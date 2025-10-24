package com.imee.portal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imee.common.result.Result;
import com.imee.portal.service.IPortalService;
import com.imee.portal.vo.PortalAlgorithmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 门户控制器
 */
@RestController
@RequestMapping("/api/portal")
public class PortalController {

    @Autowired
    private IPortalService portalService;

    /**
     * 门户首页 - 获取已发布算法
     */
    @GetMapping("/home")
    public Result<IPage<PortalAlgorithmVO>> home(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String algorithmType) {
        IPage<PortalAlgorithmVO> page = portalService.getPublishedAlgorithms(current, size, keyword, algorithmType);
        return Result.success(page);
    }

    /**
     * 门户分类列表
     */
    @GetMapping("/categories/{categoryCode}")
    public Result<IPage<PortalAlgorithmVO>> categoryAlgorithms(
            @PathVariable String categoryCode,
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        IPage<PortalAlgorithmVO> page = portalService.getAlgorithmsByCategory(categoryCode, current, size);
        return Result.success(page);
    }

    /**
     * 门户算法详情
     */
    @GetMapping("/algorithms/{id}")
    public Result<PortalAlgorithmVO> algorithmDetail(@PathVariable Long id) {
        PortalAlgorithmVO detail = portalService.getAlgorithmDetail(id);
        return Result.success(detail);
    }
}
