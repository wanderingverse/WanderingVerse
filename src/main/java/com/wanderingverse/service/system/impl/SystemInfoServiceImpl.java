package com.wanderingverse.service.system.impl;

import com.wanderingverse.model.dto.response.SystemInfoResponseDTO;
import com.wanderingverse.service.system.SystemInfoService;
import com.wanderingverse.service.system.VisitorService;
import jakarta.annotation.Resource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

import static com.wanderingverse.util.SystemUtils.*;

/**
 * @author lihui
 * @since 2025/11/13 17:24
 **/
@Service
public class SystemInfoServiceImpl implements SystemInfoService {
    @Resource
    private Environment environment;
    @Resource
    private VisitorService visitorService;

    @Override
    public Flux<SystemInfoResponseDTO> getSystemInfo() {
        return Flux.interval(Duration.ofSeconds(3)).map(tick -> buildSystemInfo());
    }


    private SystemInfoResponseDTO buildSystemInfo() {
        BigDecimal cpuUsage = getCpuUsage();
        BigDecimal memoryUsage = getMemoryUsage();
        Long onlineVisitor = visitorService.getOnlineVisitorCount();
        Long totalVisit = new Random().nextLong(1, 1000000);
        Long todayVisit = new Random().nextLong(1, 1000);
        Long monthVisit = new Random().nextLong(1, 1000000);
        Long totalUptime = getTotalUptime();
        Long continuousUptime = getJvmUptime();
        Long remainingRuntime = getRemainingRuntime();
        return new SystemInfoResponseDTO()
                .setCpuUsage(cpuUsage)
                .setMemoryUsage(memoryUsage)
                .setOnlineVisitor(String.valueOf(onlineVisitor))
                .setTotalVisit(String.valueOf(totalVisit))
                .setTodayVisit(String.valueOf(todayVisit))
                .setMonthVisit(String.valueOf(monthVisit))
                .setTotalUptime(String.valueOf(totalUptime))
                .setContinuousUptime(String.valueOf(continuousUptime))
                .setRemainingRuntime(String.valueOf(remainingRuntime));
    }

    /**
     * 获取服务器累计运行时间
     */
    private Long getTotalUptime() {
        LocalDateTime firstBootTime = LocalDateTime.parse(environment.getProperty("system.info.server.first-boot-time"));
        return Duration.between(firstBootTime, LocalDateTime.now()).getSeconds();
    }

    /**
     * 获取服务器剩余可运行时间
     */
    private Long getRemainingRuntime() {
        LocalDateTime leaseExpirationTime = LocalDateTime.parse(environment.getProperty("system.info.server.lease-expiration-time"));
        return Duration.between(LocalDateTime.now(), leaseExpirationTime).getSeconds();
    }
}
