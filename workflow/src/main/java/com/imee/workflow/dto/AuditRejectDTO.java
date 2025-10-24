package com.imee.workflow.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 审核退回请求DTO
 */
@Data
public class AuditRejectDTO {

    /**
     * 退回原因
     */
    @NotBlank(message = "退回原因不能为空")
    private String auditComment;
}
