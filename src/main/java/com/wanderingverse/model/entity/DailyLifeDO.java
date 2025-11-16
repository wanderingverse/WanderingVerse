package com.wanderingverse.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 日常生活表
 *
 * @author lihui
 * @since 2025/9/12 17:49
 */
@Data
@Accessors(chain = true)
@TableName("daily_life")
public class DailyLifeDO {

    /**
     * 日常生活表主键 id
     */
    @TableField("id")
    private String id;

    /**
     * 用户 id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 生活状态 id
     */
    @TableField("living_state_id")
    @NotNull(message = "生活状态 id 不能为空")
    private String livingStateId;

    /**
     * 开始时间
     */
    @TableField("beginning_time")
    private LocalDateTime beginningTime;

    /**
     * 结束时间
     */
    @TableField("finishing_time")
    private LocalDateTime finishingTime;

    /**
     * 花费金额（元）
     */
    @TableField("expenditure_amount")
    private BigDecimal expenditureAmount;

    /**
     * 收入金额（元）
     */
    @TableField("income_amount")
    private BigDecimal incomeAmount;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

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