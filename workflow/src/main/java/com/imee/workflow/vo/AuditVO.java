package com.imee.workflow.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 审核记录展示VO
 */
@Data
public class AuditVO {

    private Long id;
    private Long algorithmId;
    private String algorithmName;
    private String auditStatus;
    private String auditStatusDesc;
    private String auditComment;
    private Long submitBy;
    private LocalDateTime submitTime;
    private Long auditBy;
    private LocalDateTime auditTime;
}
