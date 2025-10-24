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

    // 添加setStatusDesc方法
    public void setStatusDesc(Integer statusCode) {
        if (statusCode == null) {
            this.statusDesc = "";
            return;
        }
        switch (statusCode) {
            case 0:
                this.statusDesc = "开发中";
                break;
            case 1:
                this.statusDesc = "待审核";
                break;
            case 2:
                this.statusDesc = "已发布";
                break;
            case 3:
                this.statusDesc = "已下线";
                break;
            default:
                this.statusDesc = "未知";
                break;
        }
    }

    public String getAlgorithmType() {
        return algorithmType;
    }

    public String getDescription() {
        return description;
    }
}
