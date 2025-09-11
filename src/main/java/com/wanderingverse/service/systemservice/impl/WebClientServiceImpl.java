package com.wanderingverse.service.systemservice.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanderingverse.service.systemservice.WebClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static com.wanderingverse.config.CommonConfig.CONNECT_TIMEOUT_MILLIS;
import static com.wanderingverse.config.CommonConfig.RESPONSE_TIMEOUT_MILLIS;
import static org.springframework.http.HttpMethod.*;

/**
 * @author lihui
 * @since 2025/8/18 10:30
 **/
@Slf4j
@Service
public class WebClientServiceImpl implements WebClientService {
    @Override
    public <T> byte[] fetch(String authorization, String url, HttpMethod httpMethod, Object requestBody, Class<T> responseType) {
        httpMethod = ObjectUtils.isEmpty(httpMethod) ? GET : httpMethod;
        HttpClient.Builder httpClientBuilder = HttpClient.newBuilder()
                                                         // TCP 连接超时时间
                                                         .connectTimeout(Duration.ofMillis(CONNECT_TIMEOUT_MILLIS))
                                                         // 自动跟随重定向
                                                         .followRedirects(HttpClient.Redirect.ALWAYS);
        HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
                                                            .uri(URI.create(url))
                                                            .timeout(Duration.ofMillis(RESPONSE_TIMEOUT_MILLIS));
        MediaType acceptMediaType = (responseType == byte[].class) ? MediaType.ALL : MediaType.APPLICATION_JSON;
        httpRequestBuilder.header("Accept", acceptMediaType.toString());
        if (StringUtils.hasText(authorization)) {
            httpRequestBuilder.header("Authorization", authorization);
        }
        if (httpMethod == GET || httpMethod == DELETE) {
            httpRequestBuilder.method(httpMethod.name(), HttpRequest.BodyPublishers.noBody());
        } else if (httpMethod == POST || httpMethod == PUT) {
            String json = "";
            try {
                json = new ObjectMapper().writeValueAsString(requestBody);
            } catch (Exception ignore) {
            }
            httpRequestBuilder.header("Content-Type", "application/json").method(httpMethod.name(), HttpRequest.BodyPublishers.ofString(json));
        }
        HttpRequest httpRequest = httpRequestBuilder.build();
        log.debug("授权信息: {}", authorization);
        log.debug("请求方法: {}", httpMethod);
        log.debug("请求 url: {}", url);
        log.debug("请求体: {}", requestBody);
        log.debug("响应类型: {}", responseType);
        try (HttpClient httpClient = httpClientBuilder.build()) {
            HttpResponse<byte[]> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray());
            return httpResponse.body();
        } catch (Exception e) {
            throw new RuntimeException("HTTP 请求失败：", e);
        }
    }
}
