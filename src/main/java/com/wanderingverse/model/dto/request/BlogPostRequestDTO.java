package com.wanderingverse.model.dto.request;

import com.wanderingverse.model.entity.BlogPostPO;
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
public class BlogPostRequestDTO extends BlogPostPO {

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 正文内容
     */
    private String content;
}
