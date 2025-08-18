package com.wanderingverse.service.systemservice;

import javax.net.ssl.SSLException;

/**
 * @author lihui
 * @since 2025/8/18 10:30
 **/
public interface WebClientService {
    /**
     * 通用查询
     *
     * @param authorization 授权信息
     * @param url           请求地址
     * @param httpMethod    请求方式
     * @param requestBody   请求体
     * @param responseType  响应类型
     * @return T
     * @throws SSLException SSLException
     */
    <T> T fetch(String authorization, String url, String httpMethod, Object requestBody, Class<T> responseType) throws SSLException;
}
