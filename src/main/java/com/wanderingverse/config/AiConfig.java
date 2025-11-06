package com.wanderingverse.config;

import com.wanderingverse.repository.RedisChatMemoryStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.wanderingverse.common.AiCommon.MAX_MESSAGES;

/**
 * @author lihui
 * @since 2025/11/4 17:43
 **/
@Slf4j
@Configuration
public class AiConfig {
    @Resource
    private RedisChatMemoryStore redisChatMemoryStore;

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.builder()
                                                  .id(memoryId)
                                                  .maxMessages(MAX_MESSAGES)
                                                  .chatMemoryStore(redisChatMemoryStore)
                                                  .build();
    }
}
