package com.wanderingverse.model.dto.request;

import com.wanderingverse.model.entity.BlogPostDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 博客文章请求体对象
 *
 * @author lihui
 * @since 2025/8/15 16:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogPostRequestDTO extends BlogPostDO {
    /**
     * 正文内容
     */
    private String content;
}
