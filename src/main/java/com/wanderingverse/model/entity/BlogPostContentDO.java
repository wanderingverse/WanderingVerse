package com.wanderingverse.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 博客文章内容表
 *
 * @author lihui
 * @since 2025/05/14 12:38
 */
@Data
@TableName("blog_post_content")
public class BlogPostContentDO {

    /**
     * 博客文章内容表主键 id
     */
    @TableId("id")
    private String id;

    /**
     * 正文内容
     */
    @TableField("content")
    private String content;
}