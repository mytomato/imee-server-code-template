package com.imee.statistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imee.statistics.entity.OperationLog;
import com.imee.statistics.mapper.OperationLogMapper;
import com.imee.statistics.service.IOperationLogService;
import org.springframework.stereotype.Service;

/**
 * 操作日志服务实现类
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog>
        implements IOperationLogService {

    @Override
    public void log(String module, String operationType, String operationDesc,
                    Long relatedId, String relatedType, Long operatorId) {
        OperationLog log = new OperationLog();
        log.setModule(module);
        log.setOperationType(operationType);
        log.setOperationDesc(operationDesc);
        log.setRelatedId(relatedId);
        log.setRelatedType(relatedType);
        log.setOperatorId(operatorId);

        this.save(log);
    }
}
