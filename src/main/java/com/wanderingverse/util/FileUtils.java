package com.wanderingverse.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author WanderingVerse
 * @since 2025/08/24 14:41
 */
public class FileUtils {

    /**
     * MIME 类型与文件后缀名映射
     */
    private static final Map<String, String> MIME_EXT_MAP = new HashMap<>();

    static {
        // 图片类型
        MIME_EXT_MAP.put("image/png", ".png");
        MIME_EXT_MAP.put("image/jpeg", ".jpg");
        MIME_EXT_MAP.put("image/jpg", ".jpg");
        MIME_EXT_MAP.put("image/gif", ".gif");
        MIME_EXT_MAP.put("image/bmp", ".bmp");
        MIME_EXT_MAP.put("image/webp", ".webp");
        // 文档类型
        MIME_EXT_MAP.put("application/pdf", ".pdf");
        MIME_EXT_MAP.put("text/plain", ".txt");
        MIME_EXT_MAP.put("application/msword", ".doc");
        MIME_EXT_MAP.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx");
        MIME_EXT_MAP.put("application/vnd.ms-excel", ".xls");
        MIME_EXT_MAP.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx");
    }

    /**
     * 根据 MultipartFile 获取扩展名
     * <p>如果 MIME 类型不在映射表中，返回默认扩展名：.bin
     */
    public static String getFileExtension(String contentType) {
        if (StringUtils.hasText(contentType) && MIME_EXT_MAP.containsKey(contentType.toLowerCase())) {
            return MIME_EXT_MAP.get(contentType.toLowerCase());
        }
        return ".bin";
    }

    /**
     * 根据 MultipartFile 构造文件名
     */
    public static String generateUniqueFileName(MultipartFile file) {
        String ext = getFileExtension(file.getContentType());
        return UUID.randomUUID() + ext;
    }
}
