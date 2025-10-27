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
    public static String sha256Hex(String str) {
        return DigestUtil.sha256Hex(str);
    }
}
