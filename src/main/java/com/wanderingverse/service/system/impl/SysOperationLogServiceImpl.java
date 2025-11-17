package com.wanderingverse.service.system.impl;

import com.wanderingverse.mapper.system.SysOperationLogMapper;
import com.wanderingverse.service.system.SysOperationLogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * @author lihui
 * @since 2025/11/17 15:16
 */
@Service
public class SysOperationLogServiceImpl implements SysOperationLogService {
    @Resource
    private SysOperationLogMapper sysOperationLogMapper;
}