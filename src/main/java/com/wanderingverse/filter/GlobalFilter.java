package com.wanderingverse.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lihui
 * @since 2025/11/20 15:08
 **/
@Slf4j
@Order(0)
@Component
public class GlobalFilter implements Filter {


    /**
     * Servlet 容器启动时调用
     *
     * @param filterConfig filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }


    /**
     * Servlet 处理请求前调用
     *
     * @param servletRequest  servletRequest
     * @param servletResponse servletResponse
     * @param filterChain     filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 请求放行
        filterChain.doFilter(servletRequest, servletResponse);
    }


    /**
     * Servlet 容器销毁时调用
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
