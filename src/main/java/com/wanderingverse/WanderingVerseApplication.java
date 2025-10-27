package com.wanderingverse;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * @author lihui
 * @since 2025/8/15 16:14
 */
@Slf4j
@EnableAsync
@SpringBootApplication
@EnableLogRecord(tenant = "wanderingVerse")
public class WanderingVerseApplication {
    public static void main(String[] args) {
        SpringApplication.run(WanderingVerseApplication.class, args);
    }
}
