package com.wanderingverse.config;

import com.wanderingverse.mapper.system.VersionMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 启动信息输出日志
 *
 * @author lihui
 * @since 2025/8/15 15:17
 */
@Slf4j
@Component
public class StartupLogger {
    @Resource
    private Environment environment;
    @Resource
    private VersionMapper versionMapper;

    @EventListener(ApplicationReadyEvent.class)
    public void showStartupLog(ApplicationReadyEvent applicationReadyEvent) {
        ApplicationArguments applicationArguments = applicationReadyEvent.getApplicationContext().getBean(ApplicationArguments.class);
        long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
        long totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024);
        long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
        String line = "-------------------------------------------------------------------------------";
        log.info(line);
        log.info(String.format("%-20s : %s %s", "Operating System", System.getProperty("os.name"), System.getProperty("os.version")));
        log.info(String.format("%-20s : %s", "Username", System.getProperty("user.name")));
        log.info(String.format("%-20s : %s", "User Home", System.getProperty("user.home")));
        log.info(String.format("%-20s : %s", "Working Directory", System.getProperty("user.dir")));
        log.info(line);
        log.info(String.format("%-20s : %s", "Java Version", System.getProperty("java.version")));
        log.info(String.format("%-20s : %s", "Java Home", System.getProperty("java.home")));
        log.info(String.format("%-20s : %s", "JVM Args", Arrays.toString(applicationArguments.getSourceArgs())));
        log.info(String.format("%-20s : %s", "Active Profiles", Arrays.toString(environment.getActiveProfiles())));
        log.info(String.format("%-20s : %d MB", "JVM Max Memory", maxMemory));
        log.info(String.format("%-20s : %d MB", "JVM Total Memory", totalMemory));
        log.info(String.format("%-20s : %d MB", "JVM Free Memory", freeMemory));
        log.info(line);
        log.info(String.format("%-20s : %s", "Spring Boot Version", SpringBootVersion.getVersion()));
        log.info(String.format("%-20s : %s", "MySQL Version", versionMapper.getDataBaseVersion()));
        log.info(String.format("%-20s : %s", "MySQL URL", environment.getProperty("spring.datasource.url")));
        log.info(line);
        log.info("""
                         The application has started successfully.
                          _    _                 _           _             _   _                   \s
                         | |  | |               | |         (_)           | | | |                  \s
                         | |  | | __ _ _ __   __| | ___ _ __ _ _ __   __ _| | | | ___ _ __ ___  ___\s
                         | |/\\| |/ _` | '_ \\ / _` |/ _ \\ '__| | '_ \\ / _` | | | |/ _ \\ '__/ __|/ _ \\
                         \\  /\\  / (_| | | | | (_| |  __/ |  | | | | | (_| \\ \\_/ /  __/ |  \\__ \\  __/
                          \\/  \\/ \\__,_|_| |_|\\__,_|\\___|_|  |_|_| |_|\\__, |\\___/ \\___|_|  |___/\\___|
                                                                      __/ |                        \s
                                                                     |___/                         \s
                         """);
        log.info(line);
    }
}
