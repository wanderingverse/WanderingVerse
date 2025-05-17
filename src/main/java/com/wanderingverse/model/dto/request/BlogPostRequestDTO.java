package com.wanderingverse.model.dto.request;

import com.wanderingverse.model.entity.BlogPostDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 博客文章请求体对象
 *
 * @author lihui
 * @date 2025/05/09 15:31
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogPostRequestDTO extends BlogPostDO {
    /**
     * 正文内容
     */
    private String content;
}
