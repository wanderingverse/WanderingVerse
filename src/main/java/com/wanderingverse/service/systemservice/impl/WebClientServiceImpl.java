package com.wanderingverse.service.systemservice.impl;

import com.wanderingverse.service.systemservice.WebClientService;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;

import static com.wanderingverse.config.CommonConfig.*;

/**
 * @author lihui
 * @since 2025/8/18 10:30
 **/
@Slf4j
@Service
public class WebClientServiceImpl implements WebClientService {

    private SslContext sslContext = null;

    @Override
    public <T> Mono<T> fetch(String authorization, String url, String httpMethod, Object requestBody, Class<T> responseType) {
        if (!StringUtils.hasText(httpMethod)) {
            httpMethod = "GET";
        }
        HttpMethod method = HttpMethod.valueOf(httpMethod.toUpperCase());

        // 不校验 SSL 证书
        try {
            sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } catch (Exception e) {
            log.info("创建 SSL 上下文失败：", e);
        }

        HttpClient httpClient = HttpClient.create()
                .followRedirect(true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MILLIS)
                .responseTimeout(Duration.ofMillis(RESPONSE_TIMEOUT_MILLIS))
                .secure(sslContextSpec -> sslContextSpec.sslContext(sslContext))
                .doOnConnected(connection -> connection
                        .addHandlerLast(new ReadTimeoutHandler(READ_TIMEOUT_MILLIS))
                        .addHandlerLast(new WriteTimeoutHandler(WRITE_TIMEOUT_MILLIS)));

        WebClient webClient = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
        MediaType acceptMediaType = (responseType == byte[].class) ? MediaType.ALL : MediaType.APPLICATION_JSON;

        WebClient.RequestBodySpec requestSpec = webClient.method(method).uri(url).header(HttpHeaders.AUTHORIZATION, authorization).accept(acceptMediaType);

        if (method != HttpMethod.GET && method != HttpMethod.DELETE) {
            if (!ObjectUtils.isEmpty(requestBody)) {
                requestSpec = (WebClient.RequestBodySpec) requestSpec.bodyValue(requestBody);
            }
        }
        log.debug("授权信息: {}", authorization);
        log.debug("请求方法: {}", httpMethod);
        log.debug("请求 url: {}", url);
        log.debug("请求体: {}", requestBody);
        return requestSpec.retrieve().bodyToMono(responseType).retryWhen(Retry.fixedDelay(RETRY_COUNT, Duration.ofMillis(RETRY_DELAY_MILLIS))).doOnNext(response -> log.debug("查询响应: {}", response));
    }
}
