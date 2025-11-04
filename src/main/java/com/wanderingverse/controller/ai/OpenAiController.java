package com.wanderingverse.controller.ai;

import com.wanderingverse.model.dto.request.AiRequestDTO;
import com.wanderingverse.service.ai.OpenAiService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author lihui
 * @since 2025/11/4 10:59
 **/
@Slf4j
@RestController
@RequestMapping("/ai/open_ai")
public class OpenAiController {
    @Resource
    private OpenAiService openAiService;

    @PostMapping(value = "/chat")
    public Flux<String> chat(@RequestBody @Valid AiRequestDTO aiRequest) {
        return openAiService.chat(aiRequest.getQuestion());
    }
}
