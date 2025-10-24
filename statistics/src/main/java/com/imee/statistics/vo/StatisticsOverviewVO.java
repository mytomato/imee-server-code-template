package com.imee.statistics.vo;

import lombok.Data;

import java.util.Map;

/**
 * 统计概览VO
 */
@Data
public class StatisticsOverviewVO {

    /**
     * 算法总数
     */
    private Long totalAlgorithms;

    /**
     * 各分类数量
     */
    private Map<String, Long> categoryCount;

    /**
     * 各状态数量
     */
    private Map<String, Long> statusCount;
}
