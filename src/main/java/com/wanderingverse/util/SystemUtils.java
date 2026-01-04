package com.wanderingverse.util;

import cn.hutool.system.oshi.OshiUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lihui
 * @since 2025/11/14 14:25
 **/
@Slf4j
public class SystemUtils {

    /**
     * 获取系统内存使用率
     */
    public static BigDecimal getMemoryUsage() {
        long memoryTotal = OshiUtil.getMemory().getTotal();
        long memoryAvailable = OshiUtil.getMemory().getAvailable();
        long memoryUsed = memoryTotal - memoryAvailable;
        BigDecimal totalMemory = new BigDecimal(memoryTotal);
        BigDecimal usedMemory = new BigDecimal(memoryUsed);
        BigDecimal usagePercentage = usedMemory.divide(totalMemory, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        return usagePercentage.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 获取系统 CPU 使用率
     */
    public static BigDecimal getCpuUsage() {
        double cpuUsed = OshiUtil.getCpuInfo().getUsed();
        return new BigDecimal(cpuUsed).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 获取 JVM 累计运行时长
     *
     * @return 运行时长，单位：秒
     */
    public static Long getJvmUptime() {
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        return rb.getUptime() / 1000;
    }
}

