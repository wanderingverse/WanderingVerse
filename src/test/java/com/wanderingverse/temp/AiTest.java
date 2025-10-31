package com.wanderingverse.temp;

import com.wanderingverse.config.AiConfig;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lihui
 * @since 2025/10/9 11:27
 **/
@SpringBootTest
public class AiTest {
    @Resource
    private AiConfig aiConfig;

    @Test
    void learnTest() {
        String hello = aiConfig.chat("你好");
        System.out.println(hello);
    }
}
