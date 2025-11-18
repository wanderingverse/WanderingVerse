package com.wanderingverse.service.system;

import com.wanderingverse.model.entity.SysOperationLogDO;

import java.util.List;

/**
 * @author lihui
 * @since 2025/11/17 15:16
 */
public interface SysOperationLogService {

    /**
     * 获取操作日志列表
     *
     * @return List<SysOperationLogDO>
     */
    List<SysOperationLogDO> getSysOperationLogList();


    /**
     * 写入一条操作日志
     *
     * @param operationName   操作名称
     * @param operationType   操作类型
     * @param operationResult 操作结果
     * @return boolean
     */
    boolean insertSysOperationLog(String operationName, String operationType, Byte operationResult);
}