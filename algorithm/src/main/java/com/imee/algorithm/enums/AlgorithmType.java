package com.imee.algorithm.enums;

/**
 * 算法类型枚举
 * 对应5大算法分类
 */
public enum AlgorithmType {

    /**
     * 1 - 数值模拟/机理模型
     */
    NUMERICAL_MODEL(1, "numerical_model", "数值模拟/机理模型", "大气环境、水环境、源清单等数值模型"),

    /**
     * 2 - 经验模型/统计模型
     */
    STATISTICAL_MODEL(2, "statistical_model", "经验模型/统计模型", "基于系统输入输出数据的统计关系"),

    /**
     * 3 - AI模型
     */
    AI_MODEL(3, "ai_model", "AI模型", "基于文本、图像、音频、视频、表格等多模态的AI算法"),

    /**
     * 4 - 指标算法
     */
    INDICATOR_ALGORITHM(4, "indicator_algorithm", "指标算法", "基于国/行/地/企标准的指标算法"),

    /**
     * 5 - 通用大语言模型
     */
    LLM_MODEL(5, "llm_model", "通用大语言模型", "适配过、验证过的通用大语言模型");

    private final Integer id;
    private final String code;
    private final String name;
    private final String description;

    AlgorithmType(Integer id, String code, String name, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据ID获取枚举值
     * @param id 分类ID
     * @return 对应的枚举值,不存在则返回null
     */
    public static AlgorithmType fromId(Integer id) {
        if (id == null) {
            return null;
        }
        for (AlgorithmType type : values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }

    /**
     * 根据code获取枚举值
     * @param code 分类编码
     * @return 对应的枚举值,不存在则返回null
     */
    public static AlgorithmType fromCode(String code) {
        if (code == null) {
            return null;
        }
        for (AlgorithmType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
