package com.wanderingverse.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * 诗词表 poetry
 *
 * @author lihui
 * @since 2025/10/27 12:07
 */
@Data
@Accessors(chain = true)
@TableName("poetry")
public class PoetryDO {

    /**
     * 诗词表主键 id
     */
    @TableField("id")
    private Long id;

    /**
     * 诗词名
     */
    @TableField("title")
    private String title;

    /**
     * 诗词作者
     */
    @TableField("author")
    private String author;

    /**
     * 诗词正文
     */
    @TableField("content")
    private String content;

    /**
     * 诗词标签
     */
    @TableField("category")
    private String category;

    /**
     * SHA-256
     */
    @TableField("sha_256")
    private String sha256;

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