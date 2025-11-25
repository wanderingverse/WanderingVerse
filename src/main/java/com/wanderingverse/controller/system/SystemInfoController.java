package com.wanderingverse.controller.system;

import com.wanderingverse.model.dto.response.SystemInfoResponseDTO;
import com.wanderingverse.service.system.SystemInfoService;
import com.wanderingverse.util.AjaxResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

/**
 * @author lihui
 * @since 2025/11/13 17:02
 **/
@Slf4j
@RestController
@RequestMapping("/system/info")
public class SystemInfoController {
    @Resource
    private SystemInfoService systemInfoService;

    /**
     * 获取系统信息
     */
    @GetMapping("")
    public Flux<SystemInfoResponseDTO> getSystemInfo() {
        return systemInfoService.getSystemInfo();
    }

    /**
     * 获取服务器当前时间
     */
    @GetMapping("/time")
    public AjaxResult getServerTime() {
        LocalDateTime serverTime = systemInfoService.getServerTime();
        return AjaxResult.success(serverTime);
    }
}
