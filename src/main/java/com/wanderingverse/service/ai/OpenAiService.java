package com.wanderingverse.service.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

/**
 * @author lihui
 * @since 2025/11/3 18:09
 **/
@AiService
public interface OpenAiService {

    /**
     * 向 AI 提问
     *
     * @param memoryId memoryId
     * @param question question
     * @return answer
     */
    @SystemMessage(fromResource = "prompt/SystemPrompt.md")
    Flux<String> chat(@MemoryId String memoryId, @UserMessage String question);
}
