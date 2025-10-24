package com.imee.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imee.algorithm.entity.Algorithm;
import com.imee.algorithm.enums.AlgorithmStatus;
import com.imee.algorithm.service.IAlgorithmService;
import com.imee.workflow.dto.AuditPassDTO;
import com.imee.workflow.dto.AuditRejectDTO;
import com.imee.workflow.entity.AlgorithmAudit;
import com.imee.workflow.mapper.AlgorithmAuditMapper;
import com.imee.workflow.service.IAlgorithmAuditService;
import com.imee.workflow.vo.AuditVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 算法审核服务实现类
 */
@Service
public class AlgorithmAuditServiceImpl extends ServiceImpl<AlgorithmAuditMapper, AlgorithmAudit>
        implements IAlgorithmAuditService {

    @Autowired
    private IAlgorithmService algorithmService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAuditRecord(Long algorithmId, Long submitBy) {
        // 获取算法信息
        Algorithm algorithm = algorithmService.getById(algorithmId);
        if (algorithm == null) {
            throw new RuntimeException("算法不存在");
        }

        // 创建审核记录
        AlgorithmAudit audit = new AlgorithmAudit();
        audit.setAlgorithmId(algorithmId);
        audit.setAlgorithmName(algorithm.getName());
        audit.setAuditStatus("PENDING");
        audit.setSubmitBy(submitBy);
        audit.setSubmitTime(LocalDateTime.now());

        this.save(audit);
    }

    @Override
    public IPage<AuditVO> getPendingAudits(Integer current, Integer size) {
        QueryWrapper<AlgorithmAudit> wrapper = new QueryWrapper<>();
        wrapper.eq("audit_status", "PENDING")
                .orderByDesc("submit_time");

        Page<AlgorithmAudit> page = new Page<>(current, size);
        IPage<AlgorithmAudit> result = this.page(page, wrapper);

        return result.convert(this::convertToVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void passAudit(Long auditId, AuditPassDTO dto) {
        // 获取审核记录
        AlgorithmAudit audit = this.getById(auditId);
        if (audit == null) {
            throw new RuntimeException("审核记录不存在");
        }

        if (!"PENDING".equals(audit.getAuditStatus())) {
            throw new RuntimeException("该记录已经审核过了");
        }

        // 更新审核记录
        audit.setAuditStatus("PASSED");
        audit.setAuditComment(dto.getAuditComment());
        audit.setAuditBy(1L); // TODO: 从上下文获取
        audit.setAuditTime(LocalDateTime.now());
        this.updateById(audit);

        // 更新算法状态：待审核 -> 已发布
        Algorithm algorithm = algorithmService.getById(audit.getAlgorithmId());
        if (algorithm != null && AlgorithmStatus.PENDING.getCode().equals(algorithm.getStatus())) {
            algorithm.setStatus(AlgorithmStatus.PUBLISHED.getCode());
            algorithm.setUpdateBy(1L); // TODO: 从上下文获取
            algorithmService.updateById(algorithm);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rejectAudit(Long auditId, AuditRejectDTO dto) {
        // 获取审核记录
        AlgorithmAudit audit = this.getById(auditId);
        if (audit == null) {
            throw new RuntimeException("审核记录不存在");
        }

        if (!"PENDING".equals(audit.getAuditStatus())) {
            throw new RuntimeException("该记录已经审核过了");
        }

        // 更新审核记录
        audit.setAuditStatus("REJECTED");
        audit.setAuditComment(dto.getAuditComment());
        audit.setAuditBy(1L); // TODO: 从上下文获取
        audit.setAuditTime(LocalDateTime.now());
        this.updateById(audit);

        // 更新算法状态：待审核 -> 开发中
        Algorithm algorithm = algorithmService.getById(audit.getAlgorithmId());
        if (algorithm != null && AlgorithmStatus.PENDING.getCode().equals(algorithm.getStatus())) {
            algorithm.setStatus(AlgorithmStatus.DEVELOPING.getCode());
            algorithm.setUpdateBy(1L); // TODO: 从上下文获取
            algorithmService.updateById(algorithm);
        }
    }

    @Override
    public List<AuditVO> getAuditHistory(Long algorithmId) {
        QueryWrapper<AlgorithmAudit> wrapper = new QueryWrapper<>();
        wrapper.eq("algorithm_id", algorithmId)
                .orderByDesc("create_time");

        List<AlgorithmAudit> audits = this.list(wrapper);

        return audits.stream().map(this::convertToVO).collect(Collectors.toList());
    }

    private AuditVO convertToVO(AlgorithmAudit audit) {
        AuditVO vo = new AuditVO();
        BeanUtils.copyProperties(audit, vo);

        // 设置状态描述
        switch (audit.getAuditStatus()) {
            case "PENDING":
                vo.setAuditStatusDesc("待审核");
                break;
            case "PASSED":
                vo.setAuditStatusDesc("审核通过");
                break;
            case "REJECTED":
                vo.setAuditStatusDesc("审核退回");
                break;
            default:
                vo.setAuditStatusDesc(audit.getAuditStatus());
        }

        return vo;
    }
}
