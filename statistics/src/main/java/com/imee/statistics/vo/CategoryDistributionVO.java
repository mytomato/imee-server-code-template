package com.imee.statistics.vo;

import lombok.Data;

/**
 * 分类分布VO
 */
@Data
public class CategoryDistributionVO {

    /**
     * 分类编码
     */
    private String categoryCode;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 算法数量
     */
    private Long count;

    /**
     * 占比（百分比）
     */
    private Double percentage;
}
