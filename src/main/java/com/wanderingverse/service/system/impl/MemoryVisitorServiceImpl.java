package com.wanderingverse.service.system.impl;

import com.wanderingverse.model.bo.VisitorBO;
import com.wanderingverse.service.system.VisitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lihui
 * @since 2025/11/20 15:29
 **/
@Slf4j
@Primary
@Service
public class MemoryVisitorServiceImpl implements VisitorService {
    private final ConcurrentMap<String, VisitorBO> onlineVisitorConcurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public long getOnlineVisitorCount() {
        return onlineVisitorConcurrentHashMap.values().stream().filter(VisitorBO::getOnline).count();
    }

    @Override
    public long getOfflineVisitorCount() {
        return onlineVisitorConcurrentHashMap.values().stream().filter(visitorBO -> !visitorBO.getOnline()).count();
    }

    @Override
    public long getVisitorCount() {
        return onlineVisitorConcurrentHashMap.size();
    }

    @Override
    public void onVisitorOnline(String visitorId) {
        if (!StringUtils.hasText(visitorId)) {
            return;
        }
        onlineVisitorConcurrentHashMap.compute(visitorId, (key, value) -> {
            long nowTime = System.currentTimeMillis();
            if (!ObjectUtils.isEmpty(value)) {
                return value.setLatestActiveTime(nowTime)
                            .setVisitCount(value.getVisitCount() + 1)
                            .setOnline(true);
            }
            return new VisitorBO()
                    .setIpv4(visitorId)
                    .setFirstVisitTime(nowTime)
                    .setLatestActiveTime(nowTime)
                    .setVisitCount(1L)
                    .setOnline(true);
        });
    }

    @Override
    public void onVisitorOffline(String visitorId) {
        if (!StringUtils.hasText(visitorId)) {
            return;
        }
        onlineVisitorConcurrentHashMap.compute(visitorId, (key, value) -> {
            if (ObjectUtils.isEmpty(value)) {
                return null;
            }
            return value.setOnline(false);
        });
    }

    @Override
    public VisitorBO getVisitor(String visitorId) {
        return onlineVisitorConcurrentHashMap.get(visitorId);
    }
}
