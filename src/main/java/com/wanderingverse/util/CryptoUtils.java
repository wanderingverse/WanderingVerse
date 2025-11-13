package com.wanderingverse.util;

import cn.hutool.crypto.digest.DigestUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 加解密工具类
 *
 * @author lihui
 * @since 2025/10/27 16:01
 **/
@Slf4j
public class CryptoUtils {


    /**
     * 计算 SHA-256 哈希的十六进制字符串（hex）
     *
     * @param str 字符串
     * @return String
     */
    public static String sha256Hex(String str) {
        return DigestUtil.sha256Hex(str);
    }
}
