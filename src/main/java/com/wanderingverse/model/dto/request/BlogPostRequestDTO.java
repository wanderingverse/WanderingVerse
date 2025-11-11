package com.wanderingverse.model.dto.request;

import com.wanderingverse.model.entity.BlogPostDO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


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
    @NotBlank(message = "正文内容为空")
    private String content;

    /**
     * 文章分类 id 列表
     */
    private List<Long> blogCategoryIdList;
}
