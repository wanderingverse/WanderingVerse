package com.wanderingverse.service.resourceservice.impl;

import com.wanderingverse.config.MinioConfig;
import com.wanderingverse.service.resourceservice.RandomResourcesService;
import com.wanderingverse.service.systemservice.WebClientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.util.UUID;

import static com.wanderingverse.config.CommonConfig.BACKGROUND_IMAGE_DIRECTORY;
import static com.wanderingverse.config.CommonConfig.IMAGE_DIRECTORY;
import static com.wanderingverse.config.RandomResourcesConfig.RANDOM_IMAGE_URL_1;
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

    @Override
    public ResponseEntity<byte[]> getRandomImage() {
        String url = UriComponentsBuilder.fromUriString(RANDOM_IMAGE_URL_1).build().toUriString();
        String prefix = BACKGROUND_IMAGE_DIRECTORY + File.separator + IMAGE_DIRECTORY;
//        return buildResponseEntity(readImage(prefix), MediaType.IMAGE_JPEG);
        return buildResponseEntity(readAndUpdateImage(url, prefix), MediaType.IMAGE_JPEG);
    }

    @Override
    public String getRandomText() {
        return "胡适之啊胡适之！你怎么能如此堕落！先前订下的学习计划你都忘了吗？";
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
}
