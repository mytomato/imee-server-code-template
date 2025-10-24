package com.imee.algorithm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imee.algorithm.dto.VersionCreateDTO;
import com.imee.algorithm.entity.AlgorithmVersion;
import com.imee.algorithm.vo.VersionVO;

import java.util.List;

/**
 * 算法版本服务接口
 */
public interface IAlgorithmVersionService extends IService<AlgorithmVersion> {

    /**
     * 创建新版本
     */
    Long createVersion(Long algorithmId, VersionCreateDTO dto);

    /**
     * 获取版本历史
     */
    List<VersionVO> getVersionHistory(Long algorithmId);
}
