package com.wanderingverse.service.ai;

import dev.langchain4j.data.document.Document;

import java.util.List;

/**
 * @author lihui
 * @since 2025/11/24 15:32
 **/
public interface RagDocumentLoaderService {

    /**
     * 加载 RAG 文档
     *
     * @return ist<Document>
     */
    List<Document> load();
}
