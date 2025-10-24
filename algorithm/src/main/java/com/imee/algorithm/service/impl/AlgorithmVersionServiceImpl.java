package com.imee.algorithm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imee.algorithm.dto.VersionCreateDTO;
import com.imee.algorithm.entity.AlgorithmVersion;
import com.imee.algorithm.mapper.AlgorithmVersionMapper;
import com.imee.algorithm.service.IAlgorithmVersionService;
import com.imee.algorithm.vo.VersionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 算法版本服务实现类
 */
@Service
public class AlgorithmVersionServiceImpl extends ServiceImpl<AlgorithmVersionMapper, AlgorithmVersion>
        implements IAlgorithmVersionService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createVersion(Long algorithmId, VersionCreateDTO dto) {
        // 检查版本号是否已存在
        QueryWrapper<AlgorithmVersion> wrapper = new QueryWrapper<>();
        wrapper.eq("algorithm_id", algorithmId)
                .eq("version", dto.getVersion());
        if (this.count(wrapper) > 0) {
            throw new RuntimeException("版本号已存在");
        }

        // 创建版本
        AlgorithmVersion version = new AlgorithmVersion();
        BeanUtils.copyProperties(dto, version);
        version.setAlgorithmId(algorithmId);
        version.setCreateBy(1L); // TODO: 从上下文获取

        this.save(version);

        return version.getId();
    }

    @Override
    public List<VersionVO> getVersionHistory(Long algorithmId) {
        QueryWrapper<AlgorithmVersion> wrapper = new QueryWrapper<>();
        wrapper.eq("algorithm_id", algorithmId)
                .orderByDesc("create_time");

        List<AlgorithmVersion> versions = this.list(wrapper);

        return versions.stream().map(this::convertToVO).collect(Collectors.toList());
    }

    private VersionVO convertToVO(AlgorithmVersion version) {
        VersionVO vo = new VersionVO();
        BeanUtils.copyProperties(version, vo);
        return vo;
    }
}
