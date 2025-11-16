package com.wanderingverse.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * 文章分类表 blog_category
 *
 * @author lihui
 * @since 2025/11/11 13:49
 */
@Data
@Accessors(chain = true)
@TableName("blog_category")
public class BlogCategoryDO {

    /**
     * 文章分类表主键 id
     */
    @TableId("id")
    private String id;

    /**
     * 分类名称
     */
    @TableField("category_name")
    @NotBlank(message = "分类名称为空")
    private String categoryName;

    /**
     * 排序
     */
    @NotNull(message = "排序为空")
    @TableField("sort_order")
    private String sortOrder;

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