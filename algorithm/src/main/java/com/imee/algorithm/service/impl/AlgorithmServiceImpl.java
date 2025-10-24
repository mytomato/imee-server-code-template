package com.imee.algorithm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imee.algorithm.dto.AlgorithmCreateDTO;
import com.imee.algorithm.dto.AlgorithmQueryDTO;
import com.imee.algorithm.dto.AlgorithmUpdateDTO;
import com.imee.algorithm.entity.Algorithm;
import com.imee.algorithm.enums.AlgorithmStatus;
import com.imee.algorithm.enums.AlgorithmType;
import com.imee.algorithm.mapper.AlgorithmMapper;
import com.imee.algorithm.service.IAlgorithmService;
import com.imee.algorithm.vo.AlgorithmDetailVO;
import com.imee.algorithm.vo.AlgorithmVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 算法服务实现类
 */
@Service
public class AlgorithmServiceImpl extends ServiceImpl<AlgorithmMapper, Algorithm> implements IAlgorithmService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createAlgorithm(AlgorithmCreateDTO dto) {
        // 构建实体
        Algorithm algorithm = new Algorithm();
        BeanUtils.copyProperties(dto, algorithm);

        // 设置初始状态为开发中
        algorithm.setStatus(AlgorithmStatus.DEVELOPING.getCode());

        // TODO: 从上下文获取当前用户ID
        algorithm.setCreateBy(1L);
        algorithm.setUpdateBy(1L);

        // 保存
        this.save(algorithm);

        return algorithm.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAlgorithm(Long id, AlgorithmUpdateDTO dto) {
        // 检查算法是否存在
        Algorithm algorithm = this.getById(id);
        if (algorithm == null) {
            throw new RuntimeException("算法不存在");
        }

        // 检查状态：只有开发中的算法才能修改
        if (!AlgorithmStatus.DEVELOPING.getCode().equals(algorithm.getStatus())) {
            throw new RuntimeException("只有开发中的算法才能修改");
        }

        // 更新
        BeanUtils.copyProperties(dto, algorithm);
        algorithm.setUpdateBy(1L); // TODO: 从上下文获取

        this.updateById(algorithm);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAlgorithm(Long id) {
        // 检查算法是否存在
        Algorithm algorithm = this.getById(id);
        if (algorithm == null) {
            throw new RuntimeException("算法不存在");
        }

        // 检查状态：只有开发中的算法才能删除
        if (!AlgorithmStatus.DEVELOPING.getCode().equals(algorithm.getStatus())) {
            throw new RuntimeException("只有开发中的算法才能删除");
        }

        this.removeById(id);
    }

    @Override
    public AlgorithmDetailVO getDetail(Long id) {
        Algorithm algorithm = this.getById(id);
        if (algorithm == null) {
            throw new RuntimeException("算法不存在");
        }

        return convertToDetailVO(algorithm);
    }

    @Override
    public IPage<AlgorithmVO> pageQuery(AlgorithmQueryDTO dto) {
        // 构建查询条件
        QueryWrapper<Algorithm> wrapper = new QueryWrapper<>();

        // 关键词搜索
        if (StringUtils.isNotBlank(dto.getKeyword())) {
            wrapper.and(w -> w.like("name", dto.getKeyword())
                    .or().like("description", dto.getKeyword())
                    .or().like("tags", dto.getKeyword()));
        }

        // 分类筛选
        if (StringUtils.isNotBlank(dto.getAlgorithmType())) {
            wrapper.eq("algorithm_type", dto.getAlgorithmType());
        }

        // 状态筛选
        if (StringUtils.isNotBlank(dto.getStatus())) {
            wrapper.eq("status", dto.getStatus());
        }

        // 创建人筛选
        if (dto.getCreateBy() != null) {
            wrapper.eq("create_by", dto.getCreateBy());
        }

        // 排序：最新创建的在前
        wrapper.orderByDesc("create_time");

        // 分页查询
        Page<Algorithm> page = new Page<>(dto.getCurrent(), dto.getSize());
        IPage<Algorithm> result = this.page(page, wrapper);

        // 转换为VO
        return result.convert(this::convertToVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitForAudit(Long id) {
        Algorithm algorithm = this.getById(id);
        if (algorithm == null) {
            throw new RuntimeException("算法不存在");
        }

        // 状态检查：开发中 -> 待审核
        if (!AlgorithmStatus.DEVELOPING.getCode().equals(algorithm.getStatus())) {
            throw new RuntimeException("只有开发中的算法才能提交审核");
        }

        algorithm.setStatus(AlgorithmStatus.PENDING.getCode());
        algorithm.setUpdateBy(1L); // TODO: 从上下文获取

        this.updateById(algorithm);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void offline(Long id) {
        Algorithm algorithm = this.getById(id);
        if (algorithm == null) {
            throw new RuntimeException("算法不存在");
        }

        // 状态检查：已发布 -> 已下线
        if (!AlgorithmStatus.PUBLISHED.getCode().equals(algorithm.getStatus())) {
            throw new RuntimeException("只有已发布的算法才能下线");
        }

        algorithm.setStatus(AlgorithmStatus.OFFLINE.getCode());
        algorithm.setUpdateBy(1L); // TODO: 从上下文获取

        this.updateById(algorithm);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void republish(Long id) {
        Algorithm algorithm = this.getById(id);
        if (algorithm == null) {
            throw new RuntimeException("算法不存在");
        }

        // 状态检查：已下线 -> 已发布
        if (!AlgorithmStatus.OFFLINE.getCode().equals(algorithm.getStatus())) {
            throw new RuntimeException("只有已下线的算法才能重新发布");
        }

        algorithm.setStatus(AlgorithmStatus.PUBLISHED.getCode());
        algorithm.setUpdateBy(1L); // TODO: 从上下文获取

        this.updateById(algorithm);
    }

    @Override
    public IPage<AlgorithmVO> myAlgorithms(AlgorithmQueryDTO dto, Long userId) {
        dto.setCreateBy(userId);
        return this.pageQuery(dto);
    }

    /**
     * 转换为列表VO
     */
    private AlgorithmVO convertToVO(Algorithm algorithm) {
        AlgorithmVO vo = new AlgorithmVO();
        BeanUtils.copyProperties(algorithm, vo);

        // 设置类型描述
        try {
            vo.setAlgorithmTypeDesc(AlgorithmType.fromCode(algorithm.getAlgorithmType()).getDescription());
        } catch (Exception e) {
            vo.setAlgorithmTypeDesc(algorithm.getAlgorithmType());
        }

        // 设置状态描述
        try {
            vo.setStatusDesc(AlgorithmStatus.fromCode(algorithm.getStatus()).getDescription());
        } catch (Exception e) {
            vo.setStatusDesc(algorithm.getStatus());
        }

        return vo;
    }

    /**
     * 转换为详情VO
     */
    private AlgorithmDetailVO convertToDetailVO(Algorithm algorithm) {
        AlgorithmDetailVO vo = new AlgorithmDetailVO();
        BeanUtils.copyProperties(algorithm, vo);

        // 设置类型描述
        try {
            vo.setAlgorithmTypeDesc(AlgorithmType.fromCode(algorithm.getAlgorithmType()).getDescription());
        } catch (Exception e) {
            vo.setAlgorithmTypeDesc(algorithm.getAlgorithmType());
        }

        // 设置状态描述
        try {
            vo.setStatusDesc(AlgorithmStatus.fromCode(algorithm.getStatus()).getDescription());
        } catch (Exception e) {
            vo.setStatusDesc(algorithm.getStatus());
        }

        return vo;
    }
}
