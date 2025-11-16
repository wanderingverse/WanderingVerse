package com.wanderingverse.model.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author lihui
 * @since 2025/11/13 17:04
 **/
@Data
@Accessors(chain = true)
public class SystemInfoResponseDTO {

    /**
     * CPU 使用率
     * <p>保留 2 位小数
     */
    private BigDecimal cpuUsage;

    /**
     * 内存使用率
     * <p>保留 2 位小数
     */
    private BigDecimal memoryUsage;

    /**
     * 在线访客数
     */
    private String onlineVisitor;

    /**
     * 总访问量
     */
    private String totalVisit;

    /**
     * 今日累计访问量
     */
    private String todayVisit;

    /**
     * 当月访问量
     */
    private String monthVisit;

    /**
     * 服务器累计运行时间
     * <p>单位：秒
     */
    private String totalUptime;

    /**
     * 服务器持续运行时间
     * <p>单位：秒
     */
    private String continuousUptime;

    /**
     * 服务器剩余可运行时间
     * <p>单位：秒
     */
    private String remainingRuntime;
}
