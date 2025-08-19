package com.wanderingverse.config;

import io.minio.*;
import io.minio.messages.Item;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    @PostConstruct
    private void initMinioClient() {
        minioClient = MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
    }

    /**
     * 上传文件到 minio
     *
     * @param fileName 文件名
     * @param bytes    文件字节数组
     */
    public boolean uploadFile(String fileName, byte[] bytes) {
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
    public byte[] downloadRandomFile() {
        List<String> fileNameList = getAllFileNameList();
        if (fileNameList.isEmpty()) {
            return null;
        }
        int randInt = ThreadLocalRandom.current().nextInt(0, fileNameList.size());
        String fileName = fileNameList.get(randInt);
        log.info("随机获取文件：{}", fileName);
        return downloadFile(fileName);
    }

    /**
     * 获取 bucket 中所有文件
     *
     * @return 文件名列表
     */
    public List<String> getAllFileNameList() {
        ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder().bucket(bucketName).build();
        Iterable<Result<Item>> listObjects = minioClient.listObjects(listObjectsArgs);
        List<String> fileNameList = new ArrayList<>();
        for (Result<Item> itemResult : listObjects) {
            Item item = null;
            try {
                item = itemResult.get();
            } catch (Exception e) {
                log.error("获取文件列表失败：", e);
            }
            if (!ObjectUtils.isEmpty(item)) {
                fileNameList.add(item.objectName());
            }
        }
        return fileNameList;
    }

}
