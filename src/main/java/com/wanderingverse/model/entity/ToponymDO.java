package com.wanderingverse.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 地名表
 *
 * @author WanderingVerse
 * @since 2025/11/15 21:44
 **/
@Data
@TableName("toponym")
public class ToponymDO {

    /**
     * 地名表主键 id
     */
    @TableId("id")
    private String id;

    /**
     * 父级地理位置 id
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 地名
     */
    @TableField("name")
    private String name;
}