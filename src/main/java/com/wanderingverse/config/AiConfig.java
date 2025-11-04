package com.wanderingverse.config;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lihui
 * @since 2025/11/4 17:43
 **/
@Slf4j
@Configuration
public class AiConfig {
    /**
     * 最大会话记录保存数
     */
    private static final int MAX_MESSAGES = 32;

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.builder()
                                                  .id(memoryId)
                                                  .maxMessages(MAX_MESSAGES)
                                                  .build();
    }
}
