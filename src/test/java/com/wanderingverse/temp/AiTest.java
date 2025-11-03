package com.wanderingverse.temp;

import com.wanderingverse.service.artificialintelligenceservice.OpenAiService;
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
    private OpenAiService openAiService;

    @Test
    void AiChatTest() {
        String response = openAiService.chat("Hello World");
    }
}
