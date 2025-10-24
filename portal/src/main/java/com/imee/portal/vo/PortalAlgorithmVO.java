package com.imee.portal.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 门户算法展示VO（精简版，不含敏感信息）
 */
@Data
public class PortalAlgorithmVO {

    private Long id;
    private String name;
    private String algorithmType;
    private String algorithmTypeDesc;
    private String version;
    private String description;
    private String tags;

    // 基础信息
    private String basicIntro;
    private String basicScenario;

    // 技术信息
    private String technicalPrinciple;
    private String technicalStack;

    // 输入输出
    private String ioInputParams;
    private String ioOutputParams;
    private String ioDataFormat;

    // 性能指标
    private String performanceAccuracy;
    private String performanceResponseTime;
    private String performanceThroughput;

    // 使用指南
    private String usageInvocationMethod;
    private String usageConfiguration;
    private String usageExampleCode;

    private LocalDateTime createTime;
}
