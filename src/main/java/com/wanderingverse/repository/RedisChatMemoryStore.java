package com.wanderingverse.repository;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihui
 * @since 2025/11/6 14:52
 **/
@Repository
public class RedisChatMemoryStore implements ChatMemoryStore {
    @Resource
    private RedisTemplate<String, Object> redisTemplateForChatMessageList;

    /**
     * 获取会话消息列表
     *
     * @param memoryId 会话 id
     * @return List<ChatMessage>
     */
    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        Object messagesObject = redisTemplateForChatMessageList.opsForValue().get(memoryId.toString());
        return Convert.convert(new TypeReference<>() {
        }, messagesObject);
    }


    /**
     * 新增或更新会话消息
     *
     * @param memoryId        会话 id
     * @param chatMessageList 会话消息列表
     */
    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> chatMessageList) {
        redisTemplateForChatMessageList.opsForValue().set(memoryId.toString(), chatMessageList);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        redisTemplateForChatMessageList.delete(memoryId.toString());
    }
}
