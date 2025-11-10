package com.wanderingverse.common;

/**
 * @author lihui
 * @since 2025/11/6 17:00
 **/
public class AiCommon {

    /**
     * 最大会话记录保存数
     */
    public static final int MAX_MESSAGES = 32;

    /**
     * 余弦相似度
     * between [0,1]
     */
    public static final double COSINE_SIMILARITY = 0.5;

    /**
     * 最大 RAG 检索结果数
     */
    public static final int MAX_RESULT_NUMBER_FOR_RAG = 10;
}
