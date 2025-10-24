package com.imee.algorithm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imee.algorithm.dto.AlgorithmCreateDTO;
import com.imee.algorithm.dto.AlgorithmQueryDTO;
import com.imee.algorithm.dto.AlgorithmUpdateDTO;
import com.imee.algorithm.entity.Algorithm;
import com.imee.algorithm.vo.AlgorithmDetailVO;
import com.imee.algorithm.vo.AlgorithmVO;

/**
 * 算法服务接口
 */
public interface IAlgorithmService extends IService<Algorithm> {

    /**
     * 创建算法
     */
    Long createAlgorithm(AlgorithmCreateDTO dto);

    /**
     * 更新算法
     */
    void updateAlgorithm(Long id, AlgorithmUpdateDTO dto);

    /**
     * 删除算法
     */
    void deleteAlgorithm(Long id);

    /**
     * 获取算法详情
     */
    AlgorithmDetailVO getDetail(Long id);

    /**
     * 分页查询算法列表
     */
    IPage<AlgorithmVO> pageQuery(AlgorithmQueryDTO dto);

    /**
     * 提交审核
     */
    void submitForAudit(Long id);

    /**
     * 下线算法
     */
    void offline(Long id);

    /**
     * 重新发布
     */
    void republish(Long id);

    /**
     * 我的算法列表
     */
    IPage<AlgorithmVO> myAlgorithms(AlgorithmQueryDTO dto, Long userId);
}
