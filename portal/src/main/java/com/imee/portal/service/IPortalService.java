package com.imee.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imee.portal.vo.PortalAlgorithmVO;

/**
 * 门户服务接口
 */
public interface IPortalService {

    /**
     * 门户首页 - 获取已发布算法列表
     */
    IPage<PortalAlgorithmVO> getPublishedAlgorithms(Integer current, Integer size, String keyword, String algorithmType);

    /**
     * 门户分类列表
     */
    IPage<PortalAlgorithmVO> getAlgorithmsByCategory(String categoryCode, Integer current, Integer size);

    /**
     * 门户算法详情 - 只展示公开信息
     */
    PortalAlgorithmVO getAlgorithmDetail(Long id);
}
