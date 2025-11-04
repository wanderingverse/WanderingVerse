package com.wanderingverse.controller.ai;

import com.wanderingverse.service.ai.OpenAiService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/chat")
    public Flux<String> chat(@RequestParam String question) {
        Flux<String> flux = openAiService.chat(question);
        return flux;
    }
}
