package com.wanderingverse.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
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
public class BlogPostDO {

    /**
     * 博客文章表主键 id
     */
    @TableId("id")
    private String id;

    /**
     * 文章标题
     */
    @NotBlank(message = "标题内容为空")
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
    private String authorId;

    /**
     * 正文 id
     */
    @TableField("content_id")
    private String contentId;

    /**
     * 删除状态
     * <p>0：未删除
     * <p>1：已删除
     */
    @TableField("delete_status")
    private Byte deleteStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}