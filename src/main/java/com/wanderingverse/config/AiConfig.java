package com.wanderingverse.config;

import dev.langchain4j.model.openai.OpenAiChatModel;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author lihui
 * @since 2025/10/31 10:25
 **/
@Slf4j
@Configuration
public class AiConfig {

    /**
     * 模型请求 url
     */
    @Value("https://dashscope.aliyuncs.com/compatible-mode/v1")
    private String baseUrl;

    /**
     * apiKey
     */
    @Value("sk-a08873fc46fe459cbeb8bc8bcd0ab9e3")
    private String apiKey;

    /**
     * 模型名称
     */
    @Value("qwen-plus")
    private String modelName;

    private OpenAiChatModel openAiChatModel;


    /**
     * 初始化 AI 模型
     */
    @PostConstruct
    private void initAi() {
        openAiChatModel = OpenAiChatModel.builder().baseUrl(baseUrl).apiKey(apiKey).modelName(modelName).build();
    }


    /**
     * 向 AI 提问
     *
     * @param question question
     * @return answer
     */
    public String chat(String question) {
        return openAiChatModel.chat(question);
    }
}
