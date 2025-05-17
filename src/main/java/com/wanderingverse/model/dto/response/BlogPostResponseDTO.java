package com.wanderingverse.model.dto.response;

import com.wanderingverse.model.entity.BlogPostDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 博客文章响应体对象
 *
 * @author lihui
 * @date 2025/05/14 12:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogPostResponseDTO extends BlogPostDO {
    /**
     * 正文内容
     */
    private String content;

    /**
     * 作者名称
     */
    private String authorName;
}