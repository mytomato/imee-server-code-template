package com.imee.workflow.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imee.common.result.Result;
import com.imee.workflow.dto.AuditPassDTO;
import com.imee.workflow.dto.AuditRejectDTO;
import com.imee.workflow.service.IAlgorithmAuditService;
import com.imee.workflow.vo.AuditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 算法审核控制器
 */
@RestController
@RequestMapping("/api/audits")
public class AlgorithmAuditController {

    @Autowired
    private IAlgorithmAuditService auditService;

    /**
     * 获取待审核列表
     */
    @GetMapping("/pending")
    public Result<IPage<AuditVO>> getPendingAudits(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        IPage<AuditVO> page = auditService.getPendingAudits(current, size);
        return Result.success(page);
    }

    /**
     * 审核通过
     */
    @PostMapping("/{id}/pass")
    public Result<Void> passAudit(@PathVariable Long id, @RequestBody AuditPassDTO dto) {
        auditService.passAudit(id, dto);
        return Result.success();
    }

    /**
     * 审核退回
     */
    @PostMapping("/{id}/reject")
    public Result<Void> rejectAudit(@PathVariable Long id, @RequestBody @Valid AuditRejectDTO dto) {
        auditService.rejectAudit(id, dto);
        return Result.success();
    }

    /**
     * 获取审核历史
     */
    @GetMapping("/history/{algorithmId}")
    public Result<List<AuditVO>> getAuditHistory(@PathVariable Long algorithmId) {
        List<AuditVO> history = auditService.getAuditHistory(algorithmId);
        return Result.success(history);
    }
}
