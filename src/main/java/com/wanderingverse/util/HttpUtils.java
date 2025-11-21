package com.wanderingverse.util;

import cn.hutool.core.net.Ipv4Util;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import io.minio.org.apache.commons.validator.routines.InetAddressValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import java.util.Objects;

/**
 * @author WanderingVerse
 * @since 2025/08/16 17:20
 */
public class HttpUtils {

    /**
     * 封装响应体
     */
    public static ResponseEntity<byte[]> buildResponseEntity(byte[] body, MediaType mediaType) {
        if (body == null) {
            return ResponseEntity.noContent().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentLength(body.length);
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    /**
     * 获取 HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 获取 HttpServletResponse
     */
    public static HttpServletResponse getHttpServletResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            return servletRequestAttributes.getResponse();
        }
        return null;
    }

    /**
     * 获取 User-Agent
     */
    public static UserAgent getUserAgent() {
        String userAgentString = Objects.requireNonNull(getHttpServletRequest()).getHeader(HttpHeaders.USER_AGENT);
        if (StringUtils.hasText(userAgentString)) {
            return UserAgentUtil.parse(userAgentString);
        }
        return null;
    }

    /**
     * 获取请求 URI
     */
    public static String getRequestUri() {
        return Objects.requireNonNull(getHttpServletRequest()).getRequestURI();
    }

    /**
     * 获取请求参数
     *
     * @return JSON
     */
    public static String getRequestParam() {
        Map<String, String[]> parameterMap = Objects.requireNonNull(getHttpServletRequest()).getParameterMap();
        return JSONUtil.toJsonStr(parameterMap);
    }


    /**
     * 获取客户端 IPv4
     */
    public static String getClientIp() {
        String remoteAddr = Objects.requireNonNull(getHttpServletRequest()).getRemoteAddr();
        return isValidIpv4(remoteAddr) ? remoteAddr : null;
    }

    /**
     * 校验 IPv4
     */
    public static boolean isValidIpv4(String ipv4) {
        InetAddressValidator validator = InetAddressValidator.getInstance();
        return validator.isValidInet4Address(ipv4);
    }

    /**
     * 校验 IPv6
     */
    public static boolean isValidIpv6(String ipv6) {
        InetAddressValidator validator = InetAddressValidator.getInstance();
        return validator.isValidInet6Address(ipv6);
    }

    /**
     * 校验端口号
     */
    public static boolean isValidPort(int port) {
        return port > 0 && port < 65535;
    }

    /**
     * 获取 IPv4 整型值
     */
    public static Long getIpv4Decimal(String ipv4) {
        if (!isValidIpv4(ipv4)) {
            return null;
        }
        return Ipv4Util.ipv4ToLong(ipv4);
    }
}
