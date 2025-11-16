package com.wanderingverse.model.dto.response;

import com.wanderingverse.model.entity.BlogPostDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


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

    /**
     * 文章分类 id 列表
     */
    private List<String> blogCategoryIdList;

    /**
     * 预计阅读时间，单位秒
     */
    private String readingTimeInSeconds;
}