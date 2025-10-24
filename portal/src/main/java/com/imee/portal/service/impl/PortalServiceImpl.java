package com.imee.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imee.algorithm.entity.Algorithm;
import com.imee.algorithm.enums.AlgorithmStatus;
import com.imee.algorithm.enums.AlgorithmType;
import com.imee.algorithm.service.IAlgorithmService;
import com.imee.portal.service.IPortalService;
import com.imee.portal.vo.PortalAlgorithmVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 门户服务实现类
 */
@Service
public class PortalServiceImpl implements IPortalService {

    @Autowired
    private IAlgorithmService algorithmService;

    @Override
    public IPage<PortalAlgorithmVO> getPublishedAlgorithms(Integer current, Integer size, String keyword, String algorithmType) {
        QueryWrapper<Algorithm> wrapper = new QueryWrapper<>();

        // 只查询已发布的算法
        wrapper.eq("status", AlgorithmStatus.PUBLISHED.getCode());

        // 关键词搜索
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.and(w -> w.like("name", keyword)
                    .or().like("description", keyword)
                    .or().like("tags", keyword));
        }

        // 分类筛选
        if (StringUtils.isNotBlank(algorithmType)) {
            wrapper.eq("algorithm_type", algorithmType);
        }

        // 排序：最新发布的在前
        wrapper.orderByDesc("update_time");

        // 分页查询
        Page<Algorithm> page = new Page<>(current, size);
        IPage<Algorithm> result = algorithmService.page(page, wrapper);

        // 转换为门户VO（只包含公开信息）
        return result.convert(this::convertToPortalVO);
    }

    @Override
    public IPage<PortalAlgorithmVO> getAlgorithmsByCategory(String categoryCode, Integer current, Integer size) {
        return getPublishedAlgorithms(current, size, null, categoryCode);
    }

    @Override
    public PortalAlgorithmVO getAlgorithmDetail(Long id) {
        Algorithm algorithm = algorithmService.getById(id);
        if (algorithm == null) {
            throw new RuntimeException("算法不存在");
        }

        // 只展示已发布的算法
        if (!AlgorithmStatus.PUBLISHED.getCode().equals(algorithm.getStatus())) {
            throw new RuntimeException("该算法暂未发布");
        }

        return convertToPortalVO(algorithm);
    }

    /**
     * 转换为门户VO（只包含公开信息，隐藏敏感信息）
     */
    private PortalAlgorithmVO convertToPortalVO(Algorithm algorithm) {
        PortalAlgorithmVO vo = new PortalAlgorithmVO();
        BeanUtils.copyProperties(algorithm, vo);

        // 设置类型描述
        try {
            vo.setAlgorithmTypeDesc(AlgorithmType.fromCode(algorithm.getAlgorithmType()).getDescription());
        } catch (Exception e) {
            vo.setAlgorithmTypeDesc(algorithm.getAlgorithmType());
        }

        // 注意：不包含创建人、维护人等敏感信息
        // qualityCredibility, qualityTestCoverage, qualityValidationReport
        // maintenanceMaintainer, maintenanceChangelog, maintenanceKnownIssues
        // 这些字段在Entity中有，但在PortalAlgorithmVO中没有，自动被过滤

        return vo;
    }
}
