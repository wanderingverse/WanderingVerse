package com.wanderingverse.repository;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihui
 * @since 2025/11/6 17:54
 **/
@Repository
public class ElasticSearchChatMemoryStore implements ChatMemoryStore {
    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        return null;
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> chatMessageList) {
    }

    @Override
    public void deleteMessages(Object memoryId) {
    }
}
