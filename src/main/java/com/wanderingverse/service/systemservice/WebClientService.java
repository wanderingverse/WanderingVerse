package com.wanderingverse.service.systemservice;

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
     * @return <T> byte[]
     */
    <T> byte[] fetch(String authorization, String url, String httpMethod, Object requestBody, Class<T> responseType);
}
