package com.wanderingverse.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 博客文章表
 *
 * @author lihui
 * @date 2025/05/09 15:03
 */
@Data
@TableName("blog_post")
public class BlogPostPO {

    /**
     * 博客文章表主键 id
     */
    @TableField("id")
    private Long id;

    /**
     * 文章标题
     */
    @TableField("title")
    private String title;

    /**
     * 文章摘要
     */
    @TableField("summary")
    private String summary;

    /**
     * 作者 id
     */
    @TableField("author_id")
    private Long authorId;

    /**
     * 正文 id
     */
    @TableField("content_id")
    private Long contentId;

    /**
     * 删除状态
     */
    @TableField("delete_status")
    private Byte deleteStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}