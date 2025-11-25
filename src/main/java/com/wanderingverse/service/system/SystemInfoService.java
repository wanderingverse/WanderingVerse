package com.wanderingverse.service.system;

import com.wanderingverse.model.dto.response.SystemInfoResponseDTO;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

/**
 * @author lihui
 * @since 2025/11/13 17:23
 **/
public interface SystemInfoService {

    /**
     * 获取系统信息
     *
     * @return Flux<SystemInfoResponseDTO>
     */
    Flux<SystemInfoResponseDTO> getSystemInfo();

    /**
     * 获取服务器时间
     *
     * @return LocalDateTime
     */
    LocalDateTime getServerTime();
}
