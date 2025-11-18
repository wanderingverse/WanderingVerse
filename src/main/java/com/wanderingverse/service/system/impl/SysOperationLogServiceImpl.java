package com.wanderingverse.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import com.wanderingverse.mapper.system.SysOperationLogMapper;
import com.wanderingverse.model.entity.SysOperationLogDO;
import com.wanderingverse.service.system.SysOperationLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author lihui
 * @since 2025/11/17 15:16
 */
@Slf4j
@Service
public class SysOperationLogServiceImpl implements SysOperationLogService, ILogRecordService {
    @Resource
    private SysOperationLogMapper sysOperationLogMapper;

    @Override
    public List<SysOperationLogDO> getSysOperationLogList() {
        LambdaQueryWrapper<SysOperationLogDO> sysOperationLogLambdaQueryWrapper = new LambdaQueryWrapper<SysOperationLogDO>()
                .orderByDesc(SysOperationLogDO::getCreateTime);
        return sysOperationLogMapper.selectList(sysOperationLogLambdaQueryWrapper);
    }

    @Override
    public boolean insertSysOperationLog(String operationName, String operationType, Byte operationResult) {
        SysOperationLogDO sysOperationLog = new SysOperationLogDO();
        return false;
    }

    @Override
    public void record(LogRecord logRecord) {
        log.info("记录日志：{}", logRecord);
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return null;
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return null;
    }
}