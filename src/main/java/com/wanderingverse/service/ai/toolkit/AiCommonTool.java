package com.wanderingverse.service.ai.toolkit;

import com.wanderingverse.service.system.SystemInfoService;
import dev.langchain4j.agent.tool.Tool;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author lihui
 * @since 2025/11/25 17:38
 **/
@Service
public class AiCommonTool {
    @Resource
    private SystemInfoService systemInfoService;

    @Tool("获取当前时间")
    public LocalDateTime getCurrentTime() {
        return systemInfoService.getServerTime();
    }
}
