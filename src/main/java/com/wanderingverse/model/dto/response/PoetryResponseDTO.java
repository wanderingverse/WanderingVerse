package com.wanderingverse.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author lihui
 * @since 2025/10/27 15:47
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoetryResponseDTO {

    /**
     * 诗词内容
     */
    private String content;

    /**
     * 诗词名
     */
    private String origin;

    /**
     * 诗词作者
     */
    private String author;

    /**
     * 诗词类别
     */
    private String category;
}
