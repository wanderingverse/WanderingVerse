package com.wanderingverse.service.systemservice;


import com.wanderingverse.model.entity.UserDO;

/**
 * @author lihui
 * @since 2025/8/15 16:15
 */
public interface SystemLoginService {

    /**
     * 登录
     *
     * @param user 用户信息
     * @return String token
     */
    String login(UserDO user);
}
