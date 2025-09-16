package com.wanderingverse.config;

import com.wanderingverse.interceptor.GlobalInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lihui
 * @since 2025/8/28 9:56
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private GlobalInterceptor globalInterceptor;


    /**
     * 注册自定义拦截器
     *
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor)
                // 拦截的请求
                .addPathPatterns("/**")
                // 不拦截的请求
                .excludePathPatterns("");
    }
}
