package com.imee.workflow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imee.workflow.dto.AuditPassDTO;
import com.imee.workflow.dto.AuditRejectDTO;
import com.imee.workflow.entity.AlgorithmAudit;
import com.imee.workflow.vo.AuditVO;

import java.util.List;

/**
 * 算法审核服务接口
 */
public interface IAlgorithmAuditService extends IService<AlgorithmAudit> {

    /**
     * 创建审核记录
     */
    void createAuditRecord(Long algorithmId, Long submitBy);

    /**
     * 获取待审核列表
     */
    IPage<AuditVO> getPendingAudits(Integer current, Integer size);

    /**
     * 审核通过
     */
    void passAudit(Long auditId, AuditPassDTO dto);

    /**
     * 审核退回
     */
    void rejectAudit(Long auditId, AuditRejectDTO dto);

    /**
     * 获取审核历史
     */
    List<AuditVO> getAuditHistory(Long algorithmId);
}
