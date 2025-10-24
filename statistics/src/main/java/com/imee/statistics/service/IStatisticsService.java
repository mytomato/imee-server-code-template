package com.imee.statistics.service;

import com.imee.statistics.vo.CategoryDistributionVO;
import com.imee.statistics.vo.StatisticsOverviewVO;

import java.util.List;

/**
 * 统计服务接口
 */
public interface IStatisticsService {

    /**
     * 获取统计概览
     */
    StatisticsOverviewVO getOverview();

    /**
     * 获取分类分布
     */
    List<CategoryDistributionVO> getCategoryDistribution();
}
