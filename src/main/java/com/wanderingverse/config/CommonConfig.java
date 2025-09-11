package com.wanderingverse.config;


/**
 * @author WanderingVerse
 * @since 2025/08/16 17:12
 **/
public class CommonConfig {

    /**
     * HashMap 初始大小
     */
    public static final int HASHMAP_INITIAL_CAPACITY = 16;

    /**
     * 连接超时时间（ms）
     */
    public static final int CONNECT_TIMEOUT_MILLIS = 10000;

    /**
     * 响应超时时间（ms）
     */
    public static final int RESPONSE_TIMEOUT_MILLIS = 10000;

    /**
     * 超时重试次数
     */
    public static final int RETRY_COUNT = 1;

    /**
     * 超时重试间隔（ms）
     */
    public static final int RETRY_DELAY_MILLIS = 1000;
}
