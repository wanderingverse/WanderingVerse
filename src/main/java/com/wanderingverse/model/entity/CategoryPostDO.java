package com.wanderingverse.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * 分类-文章关联表 category_post
 *
 * @author lihui
 * @since 2025/11/11 13:49
 */
@Data
@Accessors(chain = true)
@TableName("category_post")
public class CategoryPostDO {

    /**
     * 分类-文章关联表主键 id
     */
    @TableId("id")
    private Long id;

    /**
     * 文章分类表主键 id
     */
    @TableField("blog_category_id")
    private Long blogCategoryId;

    /**
     * 博客文章表主键 id
     */
    @TableField("blog_post_id")
    private Long blogPostId;

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