package com.wanderingverse.service.resource.impl;

import com.mzt.logapi.starter.annotation.LogRecord;
import com.wanderingverse.common.RandomTextTypeEnum;
import com.wanderingverse.common.SystemOperationLogNameCommon;
import com.wanderingverse.common.SystemOperationLogTypeCommon;
import com.wanderingverse.config.MinioConfig;
import com.wanderingverse.model.dto.response.PoetryResponseDTO;
import com.wanderingverse.model.entity.PoetryDO;
import com.wanderingverse.service.resource.PoetryResourceService;
import com.wanderingverse.service.resource.RandomResourcesService;
import com.wanderingverse.service.system.WebClientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.util.UUID;

import static com.wanderingverse.common.FilePathCommon.BACKGROUND_IMAGE_DIRECTORY;
import static com.wanderingverse.common.FilePathCommon.IMAGE_DIRECTORY;
import static com.wanderingverse.common.RandomResourcesCommon.RANDOM_IMAGE_URL_1;
import static com.wanderingverse.common.RandomTextTypeEnum.Poetry;
import static com.wanderingverse.util.CryptoUtils.sha256Hex;
import static com.wanderingverse.util.HttpUtils.buildResponseEntity;


/**
 * @author WanderingVerse
 * @since 2025/08/16 17:00
 **/
@Slf4j
@Service
public class RandomResourcesServiceImpl implements RandomResourcesService {
    @Resource
    private WebClientService webClientService;
    @Resource
    private MinioConfig minioConfig;

    @Resource
    @Lazy
    private PoetryResourceService poetryResourceService;

    @Override
    public ResponseEntity<byte[]> getRandomImage() {
        String url = UriComponentsBuilder.fromUriString(RANDOM_IMAGE_URL_1).build().toUriString();
        String prefix = BACKGROUND_IMAGE_DIRECTORY + File.separator + IMAGE_DIRECTORY;
//        return buildResponseEntity(readImage(prefix), MediaType.IMAGE_JPEG);
        return buildResponseEntity(readAndUpdateImage(url, prefix), MediaType.IMAGE_JPEG);
    }

    @Override
    public String getRandomImagePreSignedUrl() {
        return UriComponentsBuilder.fromUriString(RANDOM_IMAGE_URL_1).build().toUriString();
    }

    @Override
    @LogRecord(bizNo = "", type = SystemOperationLogNameCommon.GET_RESOURCE, subType = SystemOperationLogTypeCommon.QUERY, success = "{{#_ret}}")
    public String getRandomText(String randomTextType) {
        if (!StringUtils.hasText(randomTextType)) {
            randomTextType = Poetry.getRandomTextType();
        }
        RandomTextTypeEnum randomTextTypeEnum = RandomTextTypeEnum.getRandomTextTypeEnum(randomTextType);
        switch (randomTextTypeEnum) {
            case Poetry:
                return getRandomPoem(randomTextTypeEnum);
            default:
                return "";
        }
    }


    private byte[] readImage(String prefix) {
        return minioConfig.downloadRandomFile(prefix);
    }

    private byte[] readAndUpdateImage(String url, String prefix) {
        byte[] imageBytes = readImage(prefix);
        byte[] imageOfFetch = webClientService.fetch(null, url, HttpMethod.GET, null, byte[].class);
        if (imageOfFetch != null) {
            String fileName = prefix + File.separator + UUID.randomUUID() + ".jpg";
            minioConfig.uploadFileAsync(fileName, imageOfFetch);
        }
        if (imageBytes == null && imageOfFetch != null) {
            imageBytes = imageOfFetch;
        }
        return imageBytes;
    }

    private String getRandomPoem(RandomTextTypeEnum randomTextTypeEnum) {
        PoetryDO poetry = new PoetryDO();
        String url = UriComponentsBuilder.fromUriString(randomTextTypeEnum.getRandomTextUrl()).build().toUriString();
        PoetryResponseDTO poetryResponse = webClientService.fetch(null, url, HttpMethod.GET, null, PoetryResponseDTO.class);
        if (!ObjectUtils.isEmpty(poetryResponse)) {
            poetry.setTitle(poetryResponse.getOrigin())
                  .setAuthor(poetryResponse.getAuthor())
                  .setContent(poetryResponse.getContent())
                  .setCategory(poetryResponse.getCategory())
                  .setSha256(sha256Hex(poetryResponse.getContent()));
            // 异步备份至数据库
            poetryResourceService.saveAsync(poetry);
        } else {
            // 尝试从数据库获取
            poetry = poetryResourceService.getRandomPoetry();
        }
        return poetry.getContent();
    }
}
