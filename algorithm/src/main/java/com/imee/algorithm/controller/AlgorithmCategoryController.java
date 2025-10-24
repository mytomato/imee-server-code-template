package com.imee.algorithm.controller;

import com.imee.algorithm.service.IAlgorithmCategoryService;
import com.imee.algorithm.vo.CategoryVO;
import com.imee.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 算法分类控制器
 */
@RestController
@RequestMapping("/api/categories")
public class AlgorithmCategoryController {

    @Autowired
    private IAlgorithmCategoryService categoryService;

    /**
     * 获取所有分类
     */
    @GetMapping
    public Result<List<CategoryVO>> getAllCategories() {
        List<CategoryVO> categories = categoryService.getAllCategories();
        return Result.success(categories);
    }
}
