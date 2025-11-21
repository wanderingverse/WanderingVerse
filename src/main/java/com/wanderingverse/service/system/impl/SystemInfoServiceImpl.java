package com.wanderingverse.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanderingverse.mapper.system.SysOperationLogMapper;
import com.wanderingverse.model.dto.response.SystemInfoResponseDTO;
import com.wanderingverse.model.entity.SysOperationLogDO;
import com.wanderingverse.service.system.SystemInfoService;
import com.wanderingverse.service.system.VisitorService;
import jakarta.annotation.Resource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

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
    @Resource
    private SysOperationLogMapper sysOperationLogMapper;

    @Override
    public Flux<SystemInfoResponseDTO> getSystemInfo() {
        return Flux.interval(Duration.ofSeconds(3)).map(tick -> buildSystemInfo());
    }


    private SystemInfoResponseDTO buildSystemInfo() {
        BigDecimal cpuUsage = getCpuUsage();
        BigDecimal memoryUsage = getMemoryUsage();
        Long onlineVisitor = visitorService.getOnlineVisitorCount();
        Long totalVisit = getTotalVisit();
        Long todayVisit = getTodayVisit();
        Long monthVisit = getMonthVisit();
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

    /**
     * 获取系统总访问量
     */
    private Long getTotalVisit() {
        return getVisit(null, null);
    }

    /**
     * 获取系统当日累计访问量
     */
    private Long getTodayVisit() {
        LocalDateTime startTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endTime = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        return getVisit(startTime, endTime);
    }

    /**
     * 获取系统当月累计访问量
     */
    private Long getMonthVisit() {
        LocalDateTime startTime = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endTime = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);
        return getVisit(startTime, endTime);
    }

    /**
     * 获取系统指定时间段的访问量
     */
    private Long getVisit(LocalDateTime startTime, LocalDateTime endTime) {
        LambdaQueryWrapper<SysOperationLogDO> sysOperationLogLambdaQueryWrapper = new LambdaQueryWrapper<SysOperationLogDO>()
                .select(SysOperationLogDO::getUserIpv4Decimal)
                .ge(startTime != null, SysOperationLogDO::getCreateTime, startTime)
                .le(endTime != null, SysOperationLogDO::getCreateTime, endTime)
                .groupBy(SysOperationLogDO::getUserIpv4Decimal);
        List<SysOperationLogDO> sysOperationLogDOList = sysOperationLogMapper.selectList(sysOperationLogLambdaQueryWrapper);
        return (long) sysOperationLogDOList.size();
    }
}
