package com.wanderingverse.service.ai;

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
     * @param question question
     * @return answer
     */
    Flux<String> chat(String question);
}
