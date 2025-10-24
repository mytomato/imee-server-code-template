package com.imee.algorithm.vo;

import lombok.Data;

/**
 * 分类展示VO
 */
@Data
public class CategoryVO {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Long parentId;
    private Integer sortOrder;
}
