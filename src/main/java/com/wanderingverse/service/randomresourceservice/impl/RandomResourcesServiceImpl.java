package com.wanderingverse.service.randomresourceservice.impl;

import com.wanderingverse.service.randomresourceservice.RandomResourcesService;
import com.wanderingverse.service.systemservice.WebClientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import static com.wanderingverse.config.RandomResourcesConfig.RANDOM_IMAGE_URL_0;
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

    @Override
    public Mono<ResponseEntity<byte[]>> getRandomImage(String width, String height) {
        String url = UriComponentsBuilder.fromUriString(RANDOM_IMAGE_URL_0).pathSegment(width, height).build().toUriString();
        return readImageFromUrl(url).onErrorResume(throwable -> Mono.empty()).map(imageBytes -> buildResponseEntity(imageBytes, MediaType.IMAGE_JPEG));
    }


    private Mono<byte[]> readImageFromUrl(String url) {
        return webClientService.fetch(null, url, "GET", null, byte[].class);
    }
}
