package com.imee.workflow.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 算法审核记录实体类
 */
@Data
@TableName("algorithm_audit")
public class AlgorithmAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 算法ID
     */
    private Long algorithmId;

    /**
     * 算法名称（冗余字段，便于查询）
     */
    private String algorithmName;

    /**
     * 审核状态（PENDING/PASSED/REJECTED）
     */
    private String auditStatus;

    /**
     * 审核意见
     */
    private String auditComment;

    /**
     * 提交人ID
     */
    private Long submitBy;

    /**
     * 提交时间
     */
    private LocalDateTime submitTime;

    /**
     * 审核人ID
     */
    private Long auditBy;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 逻辑删除标志（0-未删除，1-已删除）
     */
    @TableLogic
    private Integer deleted;
}
