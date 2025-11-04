package com.wanderingverse.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author lihui
 * @since 2025/11/4 13:03
 **/
@Data
public class AiRequestDTO {


    /**
     * 提问内容
     */
    @NotBlank(message = "提问内容为空")
    private String question;


    private String memoryId;
}
