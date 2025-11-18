package com.wanderingverse.controller.system;

import com.wanderingverse.model.entity.SysOperationLogDO;
import com.wanderingverse.service.system.SysOperationLogService;
import com.wanderingverse.util.AjaxResult;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author lihui
 * @since 2025/11/17 15:14
 */
@Validated
@RestController
@RequestMapping("/system/log")
public class SystemLogController {
    @Resource
    private SysOperationLogService sysOperationLogService;

    /**
     * 获取操作日志列表
     */
    @GetMapping("/operation-list")
    public AjaxResult getSysOperationLogList() {
        List<SysOperationLogDO> sysOperationLogList = sysOperationLogService.getSysOperationLogList();
        return AjaxResult.success(sysOperationLogList);
    }
}