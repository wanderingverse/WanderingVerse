package com.wanderingverse.common;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录状态 HashMap
 *
 * @author WanderingVerse
 * @since 2025/09/29 20:38
 */
@Component
public class CurrentLoginStatusVariableManager {


    /**
     * 当前登录状态
     * <p> key: 用户 id
     * <p> value: 登录状态
     */
    private final ConcurrentHashMap<Long, Boolean> CurrentLoginStatusHashMap = new ConcurrentHashMap<>();


    /**
     * 新增或修改 HashMap
     */
    public void put(Long key, Boolean value) {
        if (key == null || value == null) {
            return;
        }
        CurrentLoginStatusHashMap.put(key, value);
    }

    /**
     * 删除 HashMap
     */
    public void remove(Long key) {
        if (key == null) {
            return;
        }
        CurrentLoginStatusHashMap.remove(key);
    }

    /**
     * 查找指定 key 的 value
     */
    public Boolean get(Long key) {
        if (key == null) {
            return null;
        }
        return CurrentLoginStatusHashMap.get(key);
    }
}
