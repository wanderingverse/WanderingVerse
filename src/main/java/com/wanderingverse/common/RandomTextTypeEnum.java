package com.wanderingverse.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static com.wanderingverse.common.SystemCommon.HASHMAP_INITIAL_CAPACITY;

/**
 * 随机文本类型
 *
 * @author lihui
 * @since 2025/11/12 14:52
 **/
@Getter
@AllArgsConstructor
public enum RandomTextTypeEnum {

    /**
     * 诗词
     */
    Poetry("poetry", "https://v1.jinrishici.com/all.json");


    private static final Map<String, RandomTextTypeEnum> RANDOM_TEXT_TYPE_MAP = new HashMap<>(HASHMAP_INITIAL_CAPACITY);

    static {
        for (RandomTextTypeEnum randomTextTypeEnum : values()) {
            RANDOM_TEXT_TYPE_MAP.put(randomTextTypeEnum.getRandomTextType(), randomTextTypeEnum);
        }
    }


    private final String randomTextType;
    private final String RandomTextUrl;


    /**
     * 获取随机文本类型枚举
     *
     * @param randomTextType 随机文本类型
     * @return 随机文本类型枚举
     */
    public static RandomTextTypeEnum getRandomTextTypeEnum(String randomTextType) {
        return RANDOM_TEXT_TYPE_MAP.get(randomTextType);
    }
}
