package com.imee.algorithm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imee.algorithm.entity.AlgorithmCategory;
import com.imee.algorithm.mapper.AlgorithmCategoryMapper;
import com.imee.algorithm.service.IAlgorithmCategoryService;
import com.imee.algorithm.vo.CategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 算法分类服务实现类
 */
@Service
public class AlgorithmCategoryServiceImpl extends ServiceImpl<AlgorithmCategoryMapper, AlgorithmCategory>
        implements IAlgorithmCategoryService {

    @Override
    public List<CategoryVO> getAllCategories() {
        QueryWrapper<AlgorithmCategory> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort_order");

        List<AlgorithmCategory> categories = this.list(wrapper);

        return categories.stream().map(this::convertToVO).collect(Collectors.toList());
    }

    private CategoryVO convertToVO(AlgorithmCategory category) {
        CategoryVO vo = new CategoryVO();
        BeanUtils.copyProperties(category, vo);
        return vo;
    }
}
