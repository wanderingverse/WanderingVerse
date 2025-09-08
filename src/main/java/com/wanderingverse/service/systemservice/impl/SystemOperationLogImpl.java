package com.wanderingverse.service.systemservice.impl;

import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihui
 * @since 2025/9/8 15:09
 **/
@Slf4j
@Service
public class SystemOperationLogImpl implements ILogRecordService {

    /**
     * 保存日志
     *
     * @param logRecord logRecord
     */
    @Override
    public void record(LogRecord logRecord) {
        System.out.println("保存日志：" + logRecord);
    }

    /**
     * 查询日志
     *
     * @param bizNo 业务编号
     * @param type  日志类型
     * @return List<LogRecord>
     */
    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return null;
    }


    /**
     * 根据业务编号查询日志
     *
     * @param bizNo   业务编号
     * @param type    日志类型
     * @param subType 日志子类型
     * @return List<LogRecord>
     */
    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return null;
    }
}
