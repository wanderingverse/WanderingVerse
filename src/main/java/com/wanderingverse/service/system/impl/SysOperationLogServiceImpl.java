package com.wanderingverse.service.system.impl;

import cn.hutool.http.useragent.UserAgent;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import com.wanderingverse.mapper.system.SysOperationLogMapper;
import com.wanderingverse.model.entity.SysOperationLogDO;
import com.wanderingverse.service.system.SysOperationLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.wanderingverse.util.HttpUtils.*;


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
    public boolean insertSysOperationLog(String operationName, String operationType, Byte operationResult, String operationDescription, String changeValue) {
        String userIpv4 = getClientIp();
        if (!StringUtils.hasText(userIpv4)) {
            return false;
        }
        UserAgent userAgent = getUserAgent();
        String operatingSystem = userAgent != null ? userAgent.getOs().getName() : null;
        String deviceType = userAgent != null ? userAgent.getPlatform().getName() : null;
        String browserInfo = userAgent != null ? userAgent.getBrowser().getName() : null;
        SysOperationLogDO sysOperationLog = new SysOperationLogDO()
                .setUserId(String.valueOf(getIpv4Decimal(userIpv4)))
                .setOperationName(operationName)
                .setOperationType(operationType)
                .setOperationDescription(operationDescription)
                .setOperationResult(operationResult)
                .setOperationTime(LocalDateTime.now())
                .setChangeValue(changeValue)
                .setOperationPath(getRequestUri())
                .setRequestParameter(getRequestParam())
                .setResponseParameter(null)
                .setUserIpv4(userIpv4)
                .setUserIpv4Decimal(getIpv4Decimal(userIpv4))
                .setOperatingSystem(operatingSystem)
                .setDeviceType(deviceType)
                .setBrowserInfo(browserInfo);
        return sysOperationLogMapper.insert(sysOperationLog) > 0;
    }

    @Override
    public void record(LogRecord logRecord) {
        insertSysOperationLog(logRecord.getType(), logRecord.getSubType(), (byte) (logRecord.isFail() ? 0 : 1), logRecord.getAction(), logRecord.getAction());
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