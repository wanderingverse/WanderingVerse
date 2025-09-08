package com.wanderingverse;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author lihui
 * @since 2025/8/15 16:14
 */
@Slf4j
@EnableLogRecord(tenant = "wanderingVerse")
@SpringBootApplication
public class WanderingVerseApplication {
    public static void main(String[] args) {
        SpringApplication.run(WanderingVerseApplication.class, args);
        log.info("""

                          _    _                 _           _             _   _                   \s
                         | |  | |               | |         (_)           | | | |                  \s
                         | |  | | __ _ _ __   __| | ___ _ __ _ _ __   __ _| | | | ___ _ __ ___  ___\s
                         | |/\\| |/ _` | '_ \\ / _` |/ _ \\ '__| | '_ \\ / _` | | | |/ _ \\ '__/ __|/ _ \\
                         \\  /\\  / (_| | | | | (_| |  __/ |  | | | | | (_| \\ \\_/ /  __/ |  \\__ \\  __/
                          \\/  \\/ \\__,_|_| |_|\\__,_|\\___|_|  |_|_| |_|\\__, |\\___/ \\___|_|  |___/\\___|
                                                                      __/ |                        \s
                                                                     |___/                         \s
                         """);
    }
}
