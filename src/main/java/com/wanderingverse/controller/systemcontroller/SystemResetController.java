package com.wanderingverse.controller.systemcontroller;

import com.wanderingverse.common.AjaxResult;
import com.wanderingverse.service.systemservice.SystemResetService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统重置 Controller
 *
 * @author lihui
 * @since 2025/05/09 15:16
 **/
@Slf4j
@RestController
@RequestMapping("/system/reset")
public class SystemResetController {
    @Resource
    private SystemResetService systemResetService;

    @DeleteMapping("/database")
    public AjaxResult resetDatabase() {
        boolean result = systemResetService.resetDatabase();
        return result ? AjaxResult.success("重置数据库成功") : AjaxResult.error("重置数据库失败");
    }
}
