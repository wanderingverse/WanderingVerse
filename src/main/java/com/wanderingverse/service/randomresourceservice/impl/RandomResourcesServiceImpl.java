package com.wanderingverse.service.randomresourceservice.impl;

import com.wanderingverse.service.randomresourceservice.RandomResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static com.wanderingverse.config.RandomResourcesConfig.RANDOM_IMAGE_URL_0;
import static com.wanderingverse.config.RandomResourcesConfig.RANDOM_IMAGE_URL_1;
import static com.wanderingverse.util.HttpUtils.buildResponseEntity;


/**
 * @author WanderingVerse
 * @since 2025/08/16 17:00
 **/
@Slf4j
@Service
public class RandomResourcesServiceImpl implements RandomResourcesService {
    @Override
    public ResponseEntity<byte[]> getRandomImage(String width, String height) throws URISyntaxException, IOException {
        URL url = new URI(RANDOM_IMAGE_URL_0).resolve("/" + width + "/" + height).toURL();
        InputStream inputStream;
        try {
            inputStream = url.openStream();
        } catch (IOException e) {
            log.info("图片获取失败：", e);
            url = new URI(RANDOM_IMAGE_URL_1).toURL();
            inputStream = url.openStream();
        }
        byte[] imageBytes = inputStream.readAllBytes();
        return buildResponseEntity(imageBytes, MediaType.IMAGE_JPEG);
    }
}
