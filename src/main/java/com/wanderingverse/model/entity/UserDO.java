package com.wanderingverse.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @author lihui
 * @date 2025/05/09 15:24
 */
@Data
@TableName("user")
public class UserDO {

    /**
     * 用户表主键 id
     */
    @TableId("id")
    private Long id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 性别
     */
    @TableField("gender")
    private Byte gender;

    /**
     * 头像地址
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 用户状态
     */
    @TableField("user_status")
    private Byte userStatus;

    /**
     * 删除状态
     */
    @TableField("delete_status")
    private Byte deleteStatus;

    /**
     * 最后一次登录时间
     */
    @TableField("login_time")
    private LocalDateTime loginTime;

    /**
     * 最后一次登录 IPv4
     */
    @TableField("login_ipv4")
    private String loginIpv4;

    /**
     * 最后一次登录 IPv4整型值
     */
    @TableField("login_ipv4_decimal")
    private Integer loginIpv4Decimal;

    /**
     * 创建用户 id
     */
    @TableField("create_user_id")
    private Long createUserId;

    /**
     * 更新用户 id
     */
    @TableField("update_user_id")
    private Long updateUserId;

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