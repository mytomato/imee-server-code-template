package com.imee.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imee.algorithm.entity.Algorithm;
import com.imee.algorithm.enums.AlgorithmType;
import com.imee.algorithm.service.IAlgorithmService;
import com.imee.statistics.service.IStatisticsService;
import com.imee.statistics.vo.CategoryDistributionVO;
import com.imee.statistics.vo.StatisticsOverviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计服务实现类
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService {

    @Autowired
    private IAlgorithmService algorithmService;

    @Override
    public StatisticsOverviewVO getOverview() {
        StatisticsOverviewVO overview = new StatisticsOverviewVO();

        // 获取算法总数
        long totalCount = algorithmService.count();
        overview.setTotalAlgorithms(totalCount);

        // 统计各分类数量
        Map<String, Long> categoryCount = new HashMap<>();
        for (AlgorithmType type : AlgorithmType.values()) {
            QueryWrapper<Algorithm> wrapper = new QueryWrapper<>();
            wrapper.eq("algorithm_type", type.getCode());
            long count = algorithmService.count(wrapper);
            categoryCount.put(type.getDescription(), count);
        }
        overview.setCategoryCount(categoryCount);

        // 统计各状态数量
        Map<String, Long> statusCount = new HashMap<>();
        statusCount.put("开发中", countByStatus("DEVELOPING"));
        statusCount.put("待审核", countByStatus("PENDING"));
        statusCount.put("已发布", countByStatus("PUBLISHED"));
        statusCount.put("已下线", countByStatus("OFFLINE"));
        overview.setStatusCount(statusCount);

        return overview;
    }

    @Override
    public List<CategoryDistributionVO> getCategoryDistribution() {
        List<CategoryDistributionVO> distributions = new ArrayList<>();

        // 获取总数
        long totalCount = algorithmService.count();
        if (totalCount == 0) {
            return distributions;
        }

        // 统计各分类
        for (AlgorithmType type : AlgorithmType.values()) {
            QueryWrapper<Algorithm> wrapper = new QueryWrapper<>();
            wrapper.eq("algorithm_type", type.getCode());
            long count = algorithmService.count(wrapper);

            CategoryDistributionVO distribution = new CategoryDistributionVO();
            distribution.setCategoryCode(type.getCode());
            distribution.setCategoryName(type.getDescription());
            distribution.setCount(count);
            distribution.setPercentage(count * 100.0 / totalCount);

            distributions.add(distribution);
        }

        return distributions;
    }

    private long countByStatus(String status) {
        QueryWrapper<Algorithm> wrapper = new QueryWrapper<>();
        wrapper.eq("status", status);
        return algorithmService.count(wrapper);
    }
}
