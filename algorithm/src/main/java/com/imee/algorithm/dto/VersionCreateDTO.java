package com.imee.algorithm.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 创建版本请求DTO
 */
@Data
public class VersionCreateDTO {

    @NotBlank(message = "版本号不能为空")
    private String version;

    private String description;

    private String changelog;
}
