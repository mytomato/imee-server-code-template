package com.imee.algorithm.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 版本展示VO
 */
@Data
public class VersionVO {

    private Long id;
    private Long algorithmId;
    private String version;
    private String description;
    private String changelog;
    private LocalDateTime createTime;
    private Long createBy;
}
