package com.imee.algorithm.dto;

import lombok.Data;

/**
 * 算法查询条件DTO
 */
@Data
public class AlgorithmQueryDTO {

    /**
     * 当前页码（默认1）
     */
    private Integer current = 1;

    /**
     * 每页大小（默认10）
     */
    private Integer size = 10;

    /**
     * 关键词（搜索名称、描述、标签）
     */
    private String keyword;

    /**
     * 算法类型
     */
    private String algorithmType;

    /**
     * 算法状态
     */
    private String status;

    /**
     * 创建人ID
     */
    private Long createBy;

    // 添加getter方法避免编译错误
    public String getAlgorithmType() {
        return algorithmType;
    }

    public String getStatus() {
        return status;
    }

    public String getKeyword() {
        return keyword;
    }

    public Long getCreateBy() {
        return createBy;
    }
}
