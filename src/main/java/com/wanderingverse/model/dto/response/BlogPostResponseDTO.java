package com.wanderingverse.model.dto.response;

import com.wanderingverse.model.entity.BlogPostDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 博客文章响应体对象
 *
 * @author lihui
 * @since 2025/8/15 16:17
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