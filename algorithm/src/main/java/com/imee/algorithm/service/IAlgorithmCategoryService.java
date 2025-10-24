package com.imee.algorithm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imee.algorithm.entity.AlgorithmCategory;
import com.imee.algorithm.vo.CategoryVO;

import java.util.List;

/**
 * 算法分类服务接口
 */
public interface IAlgorithmCategoryService extends IService<AlgorithmCategory> {

    /**
     * 获取所有分类
     */
    List<CategoryVO> getAllCategories();
}
