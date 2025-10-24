package com.imee.algorithm.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 算法详情展示VO
 */
@Data
public class AlgorithmDetailVO {

    private Long id;
    private String name;
    private String algorithmType;
    private String algorithmTypeDesc;
    private String version;
    private String description;
    private String tags;
    private String status;
    private String statusDesc;

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

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createBy;
    private Long updateBy;
}
