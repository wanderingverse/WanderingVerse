package com.wanderingverse.config;

import com.wanderingverse.repository.RedisChatMemoryStore;
import com.wanderingverse.service.ai.RagDocumentLoaderService;
import dev.langchain4j.community.store.embedding.redis.RedisEmbeddingStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.wanderingverse.common.AiCommon.*;

/**
 * @author lihui
 * @since 2025/11/4 17:43
 **/
@Slf4j
@Configuration
public class AiConfig {
    @Resource
    private RedisChatMemoryStore redisChatMemoryStore;
    @Resource
    private EmbeddingModel embeddingModel;
    @Resource
    private RedisEmbeddingStore redisEmbeddingStore;
    @Resource
    private RagDocumentLoaderService ragDocumentLoaderService;

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.builder()
                                                  .id(memoryId)
                                                  .maxMessages(MAX_MESSAGES)
                                                  .chatMemoryStore(redisChatMemoryStore)
                                                  .build();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        // 加载 rag 文档
        List<Document> documentList = ragDocumentLoaderService.load();
        // 文档内容分割
        DocumentSplitter documentSplitter = DocumentSplitters.recursive(MAX_CHARACTERS_PER_SEGMENT, MAX_OVERLAP_CHARACTERS);
        // 向量化、存储到向量数据库
        EmbeddingStoreIngestor embeddingStoreIngestor = EmbeddingStoreIngestor.builder()
                                                                              .embeddingStore(redisEmbeddingStore)
                                                                              .documentSplitter(documentSplitter)
                                                                              .embeddingModel(embeddingModel)
                                                                              .build();
        embeddingStoreIngestor.ingest(documentList);
        return redisEmbeddingStore;
    }

    @Bean
    public ContentRetriever contentRetriever() {
        return EmbeddingStoreContentRetriever.builder()
                                             .embeddingStore(redisEmbeddingStore)
                                             .embeddingModel(embeddingModel)
                                             // 余弦相似度，[0,1]
                                             .minScore(COSINE_SIMILARITY)
                                             // 最大返回结果数
                                             .maxResults(MAX_RESULT_NUMBER_FOR_RAG)
                                             .build();
    }
}
