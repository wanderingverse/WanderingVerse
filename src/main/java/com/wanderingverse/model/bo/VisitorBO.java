package com.wanderingverse.model.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lihui
 * @since 2025/11/20 15:53
 **/
@Data
@Accessors(chain = true)
public class VisitorBO {

    /**
     * 访客 IPv4
     */
    private String ipv4;

    /**
     * 首次访问时间
     */
    private Long firstVisitTime;

    /**
     * 最近活跃时间
     */
    private Long latestActiveTime;

    /**
     * 访问次数
     */
    private Long visitCount;

    /**
     * 在线/离线状态
     */
    private Boolean online;
}