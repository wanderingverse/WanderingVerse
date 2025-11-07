package com.wanderingverse.config;

import com.wanderingverse.repository.RedisChatMemoryStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.ClassPathDocumentLoader;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

    @Bean
    public EmbeddingStore embeddingStore() {
        // 从 resource 路径指定目录下加载所有文件（包括子目录中文件）并对应为文档（Document）
        List<Document> documentList = ClassPathDocumentLoader.loadDocumentsRecursively("./document");
        // 初始化向量数据库操作对象，用于操作 langchain4j-easy-rag 提供的、基于内存的向量数据库
        InMemoryEmbeddingStore inMemoryEmbeddingStore = new InMemoryEmbeddingStore();
        return inMemoryEmbeddingStore;
    }
}
