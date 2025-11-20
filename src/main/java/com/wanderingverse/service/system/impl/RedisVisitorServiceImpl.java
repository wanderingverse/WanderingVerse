package com.wanderingverse.service.system.impl;

import com.wanderingverse.model.bo.VisitorBO;
import com.wanderingverse.service.system.VisitorService;
import org.springframework.stereotype.Service;

/**
 * @author lihui
 * @since 2025/11/20 15:31
 **/
@Service
public class RedisVisitorServiceImpl implements VisitorService {
    @Override
    public long getOnlineVisitorCount() {
        return 0;
    }

    @Override
    public long getOfflineVisitorCount() {
        return 0;
    }

    @Override
    public long getVisitorCount() {
        return 0;
    }

    @Override
    public void onVisitorOnline(String visitorId) {

    }

    @Override
    public void onVisitorOffline(String visitorId) {

    }

    @Override
    public VisitorBO getVisitor(String visitorId) {
        return null;
    }
}
