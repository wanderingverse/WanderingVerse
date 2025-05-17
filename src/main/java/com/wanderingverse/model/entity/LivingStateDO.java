package com.wanderingverse.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 生活状态表 living_state
 *
 * @author lihui
 * @date 2025/5/13 0:20
 **/
@Data
@TableName("living_state")
public class LivingStateDO {

    /**
     * 生活状态表主键 id
     */
    @TableField("id")
    private Object id;

    /**
     * 生活状态名称
     */
    @TableField("living_state_name")
    private String livingStateName;

    /**
     * 生活状态描述
     */
    @TableField("description")
    private String description;

    /**
     * 父节点 id
     */
    @TableField("parent_id")
    private Object parentId;

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