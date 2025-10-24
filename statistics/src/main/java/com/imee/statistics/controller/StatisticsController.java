package com.imee.statistics.controller;

import com.imee.common.result.Result;
import com.imee.statistics.service.IStatisticsService;
import com.imee.statistics.vo.CategoryDistributionVO;
import com.imee.statistics.vo.StatisticsOverviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 统计控制器
 */
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private IStatisticsService statisticsService;

    /**
     * 获取数据概览
     */
    @GetMapping("/overview")
    public Result<StatisticsOverviewVO> getOverview() {
        StatisticsOverviewVO overview = statisticsService.getOverview();
        return Result.success(overview);
    }

    /**
     * 获取分类分布
     */
    @GetMapping("/category-distribution")
    public Result<List<CategoryDistributionVO>> getCategoryDistribution() {
        List<CategoryDistributionVO> distribution = statisticsService.getCategoryDistribution();
        return Result.success(distribution);
    }
}
