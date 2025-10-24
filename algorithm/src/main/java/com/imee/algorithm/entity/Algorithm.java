package com.imee.algorithm.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 算法资产实体类
 * 对应数据库表: algorithm
 * 包含七维信息模型的39个字段
 */
@TableName("algorithm")
public class Algorithm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 算法名称
     */
    private String name;

    /**
     * 算法分类ID (外键,关联algorithm_category表)
     */
    private Integer categoryId;

    /**
     * 当前版本号
     */
    private String versionNumber;

    /**
     * 算法状态: 0开发中 1待审核 2已发布 3已下线
     */
    private Integer status;

    /**
     * 算法描述
     */
    private String description;

    /**
     * 标签/关键词(逗号分隔)
     */
    private String tags;

    // ========== 权属与责任 ==========

    /**
     * 是否自主产权: 1是 0否
     */
    private Integer isProprietary;

    /**
     * 原生单位
     */
    private String originUnit;

    /**
     * 运维单位
     */
    private String maintenanceUnit;

    /**
     * 算法负责人
     */
    private String responsiblePerson;

    /**
     * 联系方式
     */
    private String contactInfo;

    // ========== 应用与场景 ==========

    /**
     * 应用场景
     */
    @TableField(value = "application_scenario", jdbcType = org.apache.ibatis.type.JdbcType.LONGVARCHAR)
    private String applicationScenario;

    /**
     * 业务域(逗号分隔)
     */
    private String businessDomain;

    /**
     * 应用案例
     */
    @TableField(value = "use_cases", jdbcType = org.apache.ibatis.type.JdbcType.LONGVARCHAR)
    private String useCases;

    // ========== 技术规格 ==========

    /**
     * 输入模态: text/image/audio/video/table/timeseries/mixed
     */
    private String inputModality;

    /**
     * 输入数据Schema(JSON格式)
     */
    @TableField(value = "input_data_schema", jdbcType = org.apache.ibatis.type.JdbcType.LONGVARCHAR)
    private String inputDataSchema;

    /**
     * 输出数据Schema(JSON格式)
     */
    @TableField(value = "output_data_schema", jdbcType = org.apache.ibatis.type.JdbcType.LONGVARCHAR)
    private String outputDataSchema;

    /**
     * 算法原理
     */
    @TableField(value = "algorithm_principle", jdbcType = org.apache.ibatis.type.JdbcType.LONGVARCHAR)
    private String algorithmPrinciple;

    /**
     * 核心优势
     */
    @TableField(value = "core_advantages", jdbcType = org.apache.ibatis.type.JdbcType.LONGVARCHAR)
    private String coreAdvantages;

    /**
     * 对标模型
     */
    private String benchmarkModel;

    // ========== 性能与评估 ==========

    /**
     * 训练数据规模
     */
    private String trainingDataSize;

    /**
     * 模型参数量(如1.3B, 7B)
     */
    private String modelParameters;

    /**
     * 准确率(百分比 0-100)
     */
    private BigDecimal accuracy;

    /**
     * 其他性能指标(JSON格式)
     */
    @TableField(value = "performance_metrics", jdbcType = org.apache.ibatis.type.JdbcType.LONGVARCHAR)
    private String performanceMetrics;

    /**
     * 评估数据集
     */
    @TableField(value = "evaluation_dataset", jdbcType = org.apache.ibatis.type.JdbcType.LONGVARCHAR)
    private String evaluationDataset;

    /**
     * 评估报告URL
     */
    private String evaluationReportUrl;

    // ========== 资源与依赖 ==========

    /**
     * 数据需求
     */
    @TableField(value = "data_requirements", jdbcType = org.apache.ibatis.type.JdbcType.LONGVARCHAR)
    private String dataRequirements;

    /**
     * 算力需求
     */
    @TableField(value = "compute_requirements", jdbcType = org.apache.ibatis.type.JdbcType.LONGVARCHAR)
    private String computeRequirements;

    /**
     * 软件依赖
     */
    @TableField(value = "software_dependencies", jdbcType = org.apache.ibatis.type.JdbcType.LONGVARCHAR)
    private String softwareDependencies;

    /**
     * 硬件依赖
     */
    @TableField(value = "hardware_dependencies", jdbcType = org.apache.ibatis.type.JdbcType.LONGVARCHAR)
    private String hardwareDependencies;

    // ========== 管理与维护 ==========

    /**
     * 查看次数
     */
    private Long viewCount;

    /**
     * 创建人ID
     */
    private String creatorId;

    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    // ========== Constructors ==========

    public Algorithm() {
    }

    // ========== Getters and Setters ==========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getIsProprietary() {
        return isProprietary;
    }

    public void setIsProprietary(Integer isProprietary) {
        this.isProprietary = isProprietary;
    }

    public String getOriginUnit() {
        return originUnit;
    }

    public void setOriginUnit(String originUnit) {
        this.originUnit = originUnit;
    }

    public String getMaintenanceUnit() {
        return maintenanceUnit;
    }

    public void setMaintenanceUnit(String maintenanceUnit) {
        this.maintenanceUnit = maintenanceUnit;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getApplicationScenario() {
        return applicationScenario;
    }

    public void setApplicationScenario(String applicationScenario) {
        this.applicationScenario = applicationScenario;
    }

    public String getBusinessDomain() {
        return businessDomain;
    }

    public void setBusinessDomain(String businessDomain) {
        this.businessDomain = businessDomain;
    }

    public String getUseCases() {
        return useCases;
    }

    public void setUseCases(String useCases) {
        this.useCases = useCases;
    }

    public String getInputModality() {
        return inputModality;
    }

    public void setInputModality(String inputModality) {
        this.inputModality = inputModality;
    }

    public String getInputDataSchema() {
        return inputDataSchema;
    }

    public void setInputDataSchema(String inputDataSchema) {
        this.inputDataSchema = inputDataSchema;
    }

    public String getOutputDataSchema() {
        return outputDataSchema;
    }

    public void setOutputDataSchema(String outputDataSchema) {
        this.outputDataSchema = outputDataSchema;
    }

    public String getAlgorithmPrinciple() {
        return algorithmPrinciple;
    }

    public void setAlgorithmPrinciple(String algorithmPrinciple) {
        this.algorithmPrinciple = algorithmPrinciple;
    }

    public String getCoreAdvantages() {
        return coreAdvantages;
    }

    public void setCoreAdvantages(String coreAdvantages) {
        this.coreAdvantages = coreAdvantages;
    }

    public String getBenchmarkModel() {
        return benchmarkModel;
    }

    public void setBenchmarkModel(String benchmarkModel) {
        this.benchmarkModel = benchmarkModel;
    }

    public String getTrainingDataSize() {
        return trainingDataSize;
    }

    public void setTrainingDataSize(String trainingDataSize) {
        this.trainingDataSize = trainingDataSize;
    }

    public String getModelParameters() {
        return modelParameters;
    }

    public void setModelParameters(String modelParameters) {
        this.modelParameters = modelParameters;
    }

    public BigDecimal getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(BigDecimal accuracy) {
        this.accuracy = accuracy;
    }

    public String getPerformanceMetrics() {
        return performanceMetrics;
    }

    public void setPerformanceMetrics(String performanceMetrics) {
        this.performanceMetrics = performanceMetrics;
    }

    public String getEvaluationDataset() {
        return evaluationDataset;
    }

    public void setEvaluationDataset(String evaluationDataset) {
        this.evaluationDataset = evaluationDataset;
    }

    public String getEvaluationReportUrl() {
        return evaluationReportUrl;
    }

    public void setEvaluationReportUrl(String evaluationReportUrl) {
        this.evaluationReportUrl = evaluationReportUrl;
    }

    public String getDataRequirements() {
        return dataRequirements;
    }

    public void setDataRequirements(String dataRequirements) {
        this.dataRequirements = dataRequirements;
    }

    public String getComputeRequirements() {
        return computeRequirements;
    }

    public void setComputeRequirements(String computeRequirements) {
        this.computeRequirements = computeRequirements;
    }

    public String getSoftwareDependencies() {
        return softwareDependencies;
    }

    public void setSoftwareDependencies(String softwareDependencies) {
        this.softwareDependencies = softwareDependencies;
    }

    public String getHardwareDependencies() {
        return hardwareDependencies;
    }

    public void setHardwareDependencies(String hardwareDependencies) {
        this.hardwareDependencies = hardwareDependencies;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Algorithm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", versionNumber='" + versionNumber + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", tags='" + tags + '\'' +
                ", isProprietary=" + isProprietary +
                ", originUnit='" + originUnit + '\'' +
                ", maintenanceUnit='" + maintenanceUnit + '\'' +
                ", responsiblePerson='" + responsiblePerson + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", viewCount=" + viewCount +
                ", creatorId='" + creatorId + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
