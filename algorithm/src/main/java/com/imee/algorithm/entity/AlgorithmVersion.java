package com.imee.algorithm.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 算法版本实体类
 * 对应数据库表: algorithm_version
 */
@TableName("algorithm_version")
public class AlgorithmVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 算法ID (外键)
     */
    @TableField("algorithm_id")
    private Long algorithmId;

    /**
     * 版本号
     */
    private String version;

    /**
     * 版本描述
     */
    private String description;

    /**
     * 版本更新日志
     */
    private String changelog;

    /**
     * 是否为主版本 (0-否, 1-是)
     */
    private Integer isMainVersion;

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

    public AlgorithmVersion() {
    }

    // ========== Getters and Setters ==========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(Long algorithmId) {
        this.algorithmId = algorithmId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public Integer getIsMainVersion() {
        return isMainVersion;
    }

    public void setIsMainVersion(Integer isMainVersion) {
        this.isMainVersion = isMainVersion;
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
        return "AlgorithmVersion{" +
                "id=" + id +
                ", algorithmId=" + algorithmId +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", changelog='" + changelog + '\'' +
                ", isMainVersion=" + isMainVersion +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}