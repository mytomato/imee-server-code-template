package com.imee.algorithm.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 算法列表展示VO
 */
@Data
public class AlgorithmVO {

    private Long id;
    private String name;
    private String algorithmType;
    private String algorithmTypeDesc;
    private String version;
    private String description;
    private String tags;
    private String status;
    private String statusDesc;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createBy;
}
