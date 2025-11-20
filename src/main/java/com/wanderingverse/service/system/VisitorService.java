package com.wanderingverse.service.system;

import com.wanderingverse.model.bo.VisitorBO;

/**
 * 访客
 *
 * @author lihui
 * @since 2025/11/20 15:28
 **/
public interface VisitorService {

    /**
     * 获取在线访客数
     *
     * @return long
     */
    long getOnlineVisitorCount();

    /**
     * 获取离线访客数
     *
     * @return long
     */
    long getOfflineVisitorCount();

    /**
     * 获取访客数
     *
     * @return long
     */
    long getVisitorCount();


    /**
     * 访客上线
     *
     * @param visitorId 访客 id
     */
    void onVisitorOnline(String visitorId);

    /**
     * 访客离线
     *
     * @param visitorId 访客 id
     */
    void onVisitorOffline(String visitorId);


    /**
     * 获取指定访客
     *
     * @param visitorId 访客 id
     * @return VisitorBO
     */
    VisitorBO getVisitor(String visitorId);

}
