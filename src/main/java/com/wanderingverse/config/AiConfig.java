package com.wanderingverse.config;

import com.wanderingverse.repository.RedisChatMemoryStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.ClassPathDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
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
        // 从 resource 路径指定目录下加载所有文件（包括子目录中文件）并对应为文档（Document）
        List<Document> documentList = ClassPathDocumentLoader.loadDocumentsRecursively("document");
        // 初始化向量数据库操作对象，用于操作 langchain4j-easy-rag 提供的基于内存的向量数据库
        InMemoryEmbeddingStore<TextSegment> inMemoryEmbeddingStore = new InMemoryEmbeddingStore<>();
        // 文档内容分割、向量化、存储到向量数据库
        EmbeddingStoreIngestor embeddingStoreIngestor = EmbeddingStoreIngestor.builder()
                                                                              .embeddingStore(inMemoryEmbeddingStore)
                                                                              .build();
        embeddingStoreIngestor.ingest(documentList);
        return inMemoryEmbeddingStore;
    }

    @Bean
    public ContentRetriever contentRetriever(EmbeddingStore<TextSegment> embeddingStore) {
        return EmbeddingStoreContentRetriever.builder()
                                             .embeddingStore(embeddingStore)
                                             // 余弦相似度，[0,1]
                                             .minScore(COSINE_SIMILARITY)
                                             // 最大返回结果数
                                             .maxResults(MAX_RESULT_NUMBER_FOR_RAG)
                                             .build();
    }
}
