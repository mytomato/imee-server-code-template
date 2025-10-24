package com.imee.workflow.dto;

import lombok.Data;

/**
 * 审核通过请求DTO
 */
@Data
public class AuditPassDTO {

    /**
     * 审核意见
     */
    private String auditComment;
}
