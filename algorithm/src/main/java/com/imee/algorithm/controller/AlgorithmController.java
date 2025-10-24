package com.imee.algorithm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imee.algorithm.dto.AlgorithmCreateDTO;
import com.imee.algorithm.dto.AlgorithmQueryDTO;
import com.imee.algorithm.dto.AlgorithmUpdateDTO;
import com.imee.algorithm.dto.VersionCreateDTO;
import com.imee.algorithm.service.IAlgorithmService;
import com.imee.algorithm.service.IAlgorithmVersionService;
import com.imee.algorithm.vo.AlgorithmDetailVO;
import com.imee.algorithm.vo.AlgorithmVO;
import com.imee.algorithm.vo.VersionVO;
import com.imee.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 算法资产控制器
 */
@RestController
@RequestMapping("/api/algorithms")
public class AlgorithmController {

    @Autowired
    private IAlgorithmService algorithmService;

    @Autowired
    private IAlgorithmVersionService versionService;

    /**
     * 创建算法
     */
    @PostMapping
    public Result<Long> create(@RequestBody @Valid AlgorithmCreateDTO dto) {
        Long id = algorithmService.createAlgorithm(dto);
        return Result.success(id);
    }

    /**
     * 更新算法
     */
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody AlgorithmUpdateDTO dto) {
        algorithmService.updateAlgorithm(id, dto);
        return Result.success();
    }

    /**
     * 删除算法
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        algorithmService.deleteAlgorithm(id);
        return Result.success();
    }

    /**
     * 获取算法详情
     */
    @GetMapping("/{id}")
    public Result<AlgorithmDetailVO> detail(@PathVariable Long id) {
        AlgorithmDetailVO detail = algorithmService.getDetail(id);
        return Result.success(detail);
    }

    /**
     * 分页查询算法列表
     */
    @GetMapping
    public Result<IPage<AlgorithmVO>> page(AlgorithmQueryDTO dto) {
        IPage<AlgorithmVO> page = algorithmService.pageQuery(dto);
        return Result.success(page);
    }

    /**
     * 提交审核
     */
    @PostMapping("/{id}/submit")
    public Result<Void> submit(@PathVariable Long id) {
        algorithmService.submitForAudit(id);
        return Result.success();
    }

    /**
     * 下线算法
     */
    @PostMapping("/{id}/offline")
    public Result<Void> offline(@PathVariable Long id) {
        algorithmService.offline(id);
        return Result.success();
    }

    /**
     * 重新发布
     */
    @PostMapping("/{id}/republish")
    public Result<Void> republish(@PathVariable Long id) {
        algorithmService.republish(id);
        return Result.success();
    }

    /**
     * 我的算法列表
     */
    @GetMapping("/my")
    public Result<IPage<AlgorithmVO>> myAlgorithms(AlgorithmQueryDTO dto) {
        // TODO: 从上下文获取当前用户ID
        Long userId = 1L;
        IPage<AlgorithmVO> page = algorithmService.myAlgorithms(dto, userId);
        return Result.success(page);
    }

    /**
     * 创建新版本
     */
    @PostMapping("/{id}/versions")
    public Result<Long> createVersion(@PathVariable Long id, @RequestBody @Valid VersionCreateDTO dto) {
        Long versionId = versionService.createVersion(id, dto);
        return Result.success(versionId);
    }

    /**
     * 获取版本历史
     */
    @GetMapping("/{id}/versions")
    public Result<List<VersionVO>> versionHistory(@PathVariable Long id) {
        List<VersionVO> versions = versionService.getVersionHistory(id);
        return Result.success(versions);
    }
}
