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
     * Redis 对话记录保存天数
     */
    public static final int MAX_SAVE_DAYS = 1;

    /**
     * 余弦相似度
     * between [0,1]
     */
    public static final double COSINE_SIMILARITY = 0.9;

    /**
     * 最大 RAG 检索结果数
     */
    public static final int MAX_RESULT_NUMBER_FOR_RAG = 10;

    /**
     * 文档分割器每个片段最大容纳字符数
     */
    public static final int MAX_CHARACTERS_PER_SEGMENT = 500;

    /**
     * 文档分割器片段间允许重叠的最大字符个数
     */
    public static final int MAX_OVERLAP_CHARACTERS = 100;
}
