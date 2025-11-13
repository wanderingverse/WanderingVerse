package com.wanderingverse.service.system.impl;

import com.wanderingverse.model.dto.response.SystemInfoResponseDTO;
import com.wanderingverse.service.system.SystemInfoService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Random;

/**
 * @author lihui
 * @since 2025/11/13 17:24
 **/
@Service
public class SystemInfoServiceImpl implements SystemInfoService {
    @Override
    public Flux<SystemInfoResponseDTO> getSystemInfo() {
        return Flux.interval(Duration.ofSeconds(1)).map(tick -> buildSystemInfo());
    }


    private SystemInfoResponseDTO buildSystemInfo() {
        BigDecimal cpuUsage = BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.HALF_UP);
        BigDecimal memoryUsage = BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.HALF_UP);
        Long onlineVisitor = new Random().nextLong(100);
        Long totalVisit = new Random().nextLong(1000000);
        Long todayVisit = new Random().nextLong(1000);
        Long monthVisit = new Random().nextLong(1000000);
        Long totalUptime = new Random().nextLong(100000);
        Long continuousUptime = new Random().nextLong(1000000);
        Long remainingRuntime = new Random().nextLong(1000);
        return new SystemInfoResponseDTO()
                .setCpuUsage(cpuUsage)
                .setMemoryUsage(memoryUsage)
                .setOnlineVisitor(onlineVisitor)
                .setTotalVisit(totalVisit)
                .setTodayVisit(todayVisit)
                .setMonthVisit(monthVisit)
                .setTotalUptime(totalUptime)
                .setContinuousUptime(continuousUptime)
                .setRemainingRuntime(remainingRuntime);
    }
}
