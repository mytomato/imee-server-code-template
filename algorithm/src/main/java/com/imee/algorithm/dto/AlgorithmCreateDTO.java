package com.imee.algorithm.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 创建算法请求DTO
 */
@Data
public class AlgorithmCreateDTO {

    @NotBlank(message = "算法名称不能为空")
    private String name;

    @NotBlank(message = "算法类型不能为空")
    private String algorithmType;

    @NotBlank(message = "版本号不能为空")
    private String version;

    private String description;

    private String tags;

    // 基础信息
    private String basicIntro;
    private String basicScenario;
    private String basicCreator;

    // 技术信息
    private String technicalPrinciple;
    private String technicalStack;
    private String technicalDependencies;

    // 输入输出
    private String ioInputParams;
    private String ioOutputParams;
    private String ioDataFormat;

    // 性能指标
    private String performanceAccuracy;
    private String performanceResponseTime;
    private String performanceThroughput;

    // 质量评估
    private String qualityCredibility;
    private String qualityTestCoverage;
    private String qualityValidationReport;

    // 使用指南
    private String usageInvocationMethod;
    private String usageConfiguration;
    private String usageExampleCode;

    // 维护信息
    private String maintenanceMaintainer;
    private String maintenanceChangelog;
    private String maintenanceKnownIssues;
}
