package com.wanderingverse.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lihui
 * @since 2025/8/27 19:28
 **/
@Slf4j
@Component
public class GlobalInterceptor implements HandlerInterceptor {


    /**
     * Controller 方法调用前执行
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return boolean
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        return true;
    }


    /**
     * Controller 方法调用正常返回后执行
     *
     * @param request      request
     * @param response     response
     * @param handler      handler
     * @param modelAndView modelAndView
     */
    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @NonNull ModelAndView modelAndView) throws InterruptedException {
    }


    /**
     * Controller 方法调用完成后执行，无论是否发生了异常
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @param ex       若没有异常则为 null
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @NonNull @Nullable Exception ex) {
    }
}
