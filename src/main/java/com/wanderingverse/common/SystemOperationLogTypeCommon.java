package com.wanderingverse.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author WanderingVerse
 * @since 2025/11/18 20:57
 */
@Getter
@AllArgsConstructor
public class SystemOperationLogTypeCommon {
    /**
     * 新增
     */
    public static final String ADD = "ADD";

    /**
     * 修改
     */
    public static final String UPDATE = "UPDATE";

    /**
     * 删除
     */
    public static final String DELETE = "DELETE";

    /**
     * 查询
     */
    public static final String QUERY = "QUERY";

    /**
     * 登录
     */
    public static final String LOGIN = "LOGIN";

    /**
     * 登出
     */
    public static final String LOGOUT = "LOGOUT";
}
