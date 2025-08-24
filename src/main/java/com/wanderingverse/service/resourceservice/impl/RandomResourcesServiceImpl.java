package com.wanderingverse.service.resourceservice.impl;

import com.wanderingverse.config.MinioConfig;
import com.wanderingverse.service.resourceservice.RandomResourcesService;
import com.wanderingverse.service.systemservice.WebClientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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
    @Resource
    private MinioConfig minioConfig;

    @Override
    public Mono<ResponseEntity<byte[]>> getRandomImage(String width, String height) {
        String url = UriComponentsBuilder.fromUriString(RANDOM_IMAGE_URL_0).pathSegment(width, height).build().toUriString();
        return readImage(url)
                .flatMap(imageBytes -> Mono.just(buildResponseEntity(imageBytes, MediaType.IMAGE_JPEG)))
                .onErrorResume(throwable -> Mono.empty());
    }


    private Mono<byte[]> readImage(String url) {
        byte[] imageBytes = minioConfig.downloadRandomFile();
        webClientService.fetch(null, url, "GET", null, byte[].class)
                .doOnNext(imageOfFetch -> {
                    Mono.fromRunnable(() -> minioConfig.uploadFile(System.currentTimeMillis() + ".jpg", imageOfFetch))
                            .subscribeOn(Schedulers.boundedElastic())
                            .subscribe();
                })
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
        if (imageBytes == null) {
            return Mono.empty();
        } else {
            return Mono.just(imageBytes);
        }
    }
}
