package com.imee.algorithm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imee.algorithm.entity.Algorithm;
import org.apache.ibatis.annotations.Mapper;

/**
 * 算法资产Mapper接口
 */
@Mapper
public interface AlgorithmMapper extends BaseMapper<Algorithm> {
}
