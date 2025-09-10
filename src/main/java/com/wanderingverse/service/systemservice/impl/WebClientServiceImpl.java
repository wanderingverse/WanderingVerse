package com.wanderingverse.service.systemservice.impl;


import com.wanderingverse.config.CommonConfig;
import com.wanderingverse.service.systemservice.WebClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.http.HttpClient;
import java.time.Duration;

/**
 * @author lihui
 * @since 2025/8/18 10:30
 **/
@Slf4j
@Service
public class WebClientServiceImpl implements WebClientService {
    @Override
    public <T> byte[] fetch(String authorization, String url, String httpMethod, Object requestBody, Class<T> responseType) {
        if (!StringUtils.hasText(httpMethod)) {
            httpMethod = "GET";
        }
        HttpMethod method = HttpMethod.valueOf(httpMethod.toUpperCase());
        HttpClient.Builder builder = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(CommonConfig.CONNECT_TIMEOUT_MILLIS)).followRedirects(HttpClient.Redirect.NORMAL);
        try (HttpClient httpClient = builder.build()) {

        }
        MediaType acceptMediaType = (responseType == byte[].class) ? MediaType.ALL : MediaType.APPLICATION_JSON;
        if (method != HttpMethod.GET && method != HttpMethod.DELETE) {
        }
        log.debug("授权信息: {}", authorization);
        log.debug("请求方法: {}", httpMethod);
        log.debug("请求 url: {}", url);
        log.debug("请求体: {}", requestBody);
        log.debug("响应类型: {}", responseType);

    }
}
