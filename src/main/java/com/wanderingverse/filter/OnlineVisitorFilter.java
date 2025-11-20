package com.wanderingverse.filter;

import com.wanderingverse.service.system.VisitorService;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lihui
 * @since 2025/11/20 15:33
 **/
@Slf4j
@Order(1)
@Component
public class OnlineVisitorFilter implements Filter {
    @Resource
    private VisitorService visitorService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String ipv4 = servletRequest.getRemoteAddr();
        visitorService.onVisitorOnline(ipv4);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
