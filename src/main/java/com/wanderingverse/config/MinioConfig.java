package com.wanderingverse.config;

import com.wanderingverse.util.FileUtils;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author lihui
 * @since 2025/8/19 11:02
 **/
@Slf4j
@Configuration
public class MinioConfig {

    @Value("${minio.url}")
    private String url;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.bucket-name}")
    private String bucketName;

    private MinioClient minioClient;
    private MinioAsyncClient minioAsyncClient;

    @PostConstruct
    private void initMinioClient() {
        minioClient = MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
    }

    @PostConstruct
    private void initMinioAsyncClient() {
        minioAsyncClient = MinioAsyncClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
    }

    /**
     * 上传文件到 minio
     *
     * @param fileName 文件名
     * @param bytes    文件字节数组
     */
    public boolean uploadFile(String fileName, byte[] bytes) {
        if (!StringUtils.hasText(fileName) || bytes == null) {
            return false;
        }
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                                                   .bucket(bucketName)
                                                   .object(fileName)
                                                   .stream(new ByteArrayInputStream(bytes), bytes.length, -1)
                                                   .build();
        try {
            minioClient.putObject(putObjectArgs);
        } catch (Exception e) {
            log.error("上传文件到 minio 失败：", e);
            return false;
        }
        log.info("上传文件到 minio 成功：{}", fileName);
        return true;
    }


    /**
     * 异步上传文件到 minio
     *
     * @param fileName 文件名
     * @param bytes    文件字节数组
     */
    public void uploadFileAsync(String fileName, byte[] bytes) {
        if (!StringUtils.hasText(fileName) || bytes == null) {
            return;
        }
        // 计算文件 Hash，文件若存在，跳过上传
        String fileHash = FileUtils.getFileMd5(bytes);
        List<Item> fileList = getAllFileList(null);
        for (Item item : fileList) {
            if (item.etag().equals(fileHash)) {
                log.info("文件已存在。fileName = {}，md5 = {}", fileName, fileHash);
                return;
            }
        }
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                                                   .bucket(bucketName)
                                                   .object(fileName)
                                                   .stream(new ByteArrayInputStream(bytes), bytes.length, -1)
                                                   .build();
        try {
            minioAsyncClient.putObject(putObjectArgs)
                            .thenApply(response -> {
                                log.info("异步上传文件到 minio 成功：{}", fileName);
                                return response;
                            })
                            .exceptionally(throwable -> {
                                log.error("异步上传文件到 minio 失败：", throwable);
                                return null;
                            });
        } catch (Exception e) {
            log.error("异步上传文件到 minio 失败：", e);
        }
    }


    /**
     * 从 minio 下载文件
     *
     * @param fileName 文件名
     * @return 文件字节数组
     */
    public byte[] downloadFile(String fileName) {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                                                   .bucket(bucketName)
                                                   .object(fileName)
                                                   .build();
        try (GetObjectResponse objectResponse = minioClient.getObject(getObjectArgs)) {
            return objectResponse.readAllBytes();
        } catch (Exception e) {
            log.error("下载文件失败：", e);
            return null;
        }
    }

    /**
     * 从 minio 随机获取一个文件
     *
     * @return 文件字节数组
     */
    public byte[] downloadRandomFile(String prefix) {
        List<Item> fileList = getAllFileList(prefix);
        if (ObjectUtils.isEmpty(fileList)) {
            return null;
        }
        int randInt = ThreadLocalRandom.current().nextInt(0, fileList.size());
        Item item = fileList.get(randInt);
        return downloadFile(item.objectName());
    }


    /**
     * 获取文件预签名访问 url
     *
     * @param fileName 文件名
     * @param expires  过期时间
     * @return 访问 url
     */
    public String getPreSignedUrl(String fileName, Integer expires) {
        if (!StringUtils.hasText(fileName)) {
            return null;
        }
        if (expires == null || expires <= 0) {
            expires = 60;
        }
        GetPresignedObjectUrlArgs getPresignedObjectUrlArgs = GetPresignedObjectUrlArgs.builder()
                                                                                       .method(Method.GET)
                                                                                       .bucket(bucketName)
                                                                                       .object(fileName)
                                                                                       .expiry(expires, TimeUnit.SECONDS)
                                                                                       .build();
        try {
            return minioClient.getPresignedObjectUrl(getPresignedObjectUrlArgs);
        } catch (Exception e) {
            log.error("获取文件 url 失败：", e);
            return null;
        }
    }

    /**
     * 获取指定文件的详细信息
     */
    public StatObjectResponse getFileInfo(String fileName) {
        StatObjectResponse statObjectResponse = null;
        StatObjectArgs statObjectArgs = StatObjectArgs.builder().bucket(bucketName).object(fileName).build();
        try {
            statObjectResponse = minioClient.statObject(statObjectArgs);
        } catch (Exception e) {
            log.error("获取文件信息失败：", e);
        }
        return statObjectResponse;
    }

    /**
     * 获取 bucket 中指定前缀的所有文件列表
     *
     * @return 文件列表
     */
    public List<Item> getAllFileList(String prefix) {
        ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder().bucket(bucketName).prefix(prefix).build();
        Iterable<Result<Item>> listObjects = minioClient.listObjects(listObjectsArgs);
        List<Item> fileList = new ArrayList<>();
        for (Result<Item> itemResult : listObjects) {
            Item item = null;
            try {
                item = itemResult.get();
            } catch (Exception e) {
                log.error("获取文件列表失败：", e);
            }
            if (!ObjectUtils.isEmpty(item)) {
                fileList.add(item);
            }
        }
        return fileList;
    }

    /**
     * 删除文件
     */
    public boolean deleteFile(String fileName) {
        if (!StringUtils.hasText(fileName)) {
            return false;
        }
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build();
        try {
            minioClient.removeObject(removeObjectArgs);
        } catch (Exception e) {
            log.error("删除文件失败：", e);
            return false;
        }
        return true;
    }

    /**
     * 移动文件
     *
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @return boolean
     */
    public boolean moveFile(String sourceFile, String targetFile) {
        throw new UnsupportedOperationException();
    }
}
