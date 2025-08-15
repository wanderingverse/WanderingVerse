package com.wanderingverse.config;

import com.wanderingverse.mapper.systemmapper.VersionMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 启动时信息输出日志
 *
 * @author lihui
 * @since 2025/8/15 15:17
 */
@Slf4j
@Component
public class StartupLogger implements ApplicationRunner {
    private final long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
    private final long totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024);
    private final long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
    @Resource
    private Environment environment;
    @Resource
    private VersionMapper versionMapper;

    @Override
    public void run(ApplicationArguments args) {
        log.info("============================================================");
        log.info("                      系统启动信息");
        log.info("============================================================");
        log.info("【系统信息】");
        log.info("  操作系统：         {} {}", System.getProperty("os.name"), System.getProperty("os.version"));
        log.info("  用户名：           {}", System.getProperty("user.name"));
        log.info("  用户目录：         {}", System.getProperty("user.home"));
        log.info("  工作目录：         {}", System.getProperty("user.dir"));
        log.info("------------------------------------------------------------");
        log.info("【Java 信息】");
        log.info("  Java版本：         {}", System.getProperty("java.version"));
        log.info("  Java路径：         {}", System.getProperty("java.home"));
        log.info("  命令行参数：        {}", args.getOptionNames());
        log.info("  运行环境：          {}", Arrays.toString(environment.getActiveProfiles()));
        log.info("  JVM 最大可用内存：   {} MB", maxMemory);
        log.info("  JVM 已分配内存：     {} MB", totalMemory);
        log.info("  JVM 空闲内存：      {} MB", freeMemory);
        log.info("------------------------------------------------------------");
        log.info("【服务信息】");
        log.info("  Spring Boot版本：  {}", SpringBootVersion.getVersion());
        log.info("  MySQL 版本：       {}", versionMapper.getDataBaseVersion());
        log.info("  MySQL URL：       {}", environment.getProperty("spring.datasource.url"));
        log.info("============================================================");
    }
}
