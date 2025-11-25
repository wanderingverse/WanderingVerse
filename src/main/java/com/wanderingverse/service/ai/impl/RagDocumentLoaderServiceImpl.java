package com.wanderingverse.service.ai.impl;

import com.wanderingverse.service.ai.RagDocumentLoaderService;
import dev.langchain4j.data.document.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lihui
 * @since 2025/11/24 15:33
 **/
@Service
public class RagDocumentLoaderServiceImpl implements RagDocumentLoaderService {
    @Override
    public List<Document> load() {
        List<Document> documentList = new ArrayList<>();
        documentList.add(Document.from("我的名字叫荒草"));
        documentList.add(Document.from("今天天气是阴天"));
        return documentList;
    }
}
