package com.imee.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imee.statistics.entity.OperationLog;

/**
 * 操作日志服务接口
 */
public interface IOperationLogService extends IService<OperationLog> {

    /**
     * 记录操作日志
     */
    void log(String module, String operationType, String operationDesc,
             Long relatedId, String relatedType, Long operatorId);
}
