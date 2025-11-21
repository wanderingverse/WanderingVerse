package com.wanderingverse.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * 操作日志表
 *
 * @author lihui
 * @since 2025/11/17 15:14
 */
@Data
@Accessors(chain = true)
@TableName("sys_operation_log")
public class SysOperationLogDO {

    /**
     * 操作日志表主键
     */
    @TableId("id")
    private String id;

    /**
     * 用户 id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 操作名称
     */
    @TableField("operation_name")
    private String operationName;

    /**
     * 操作类型
     */
    @TableField("operation_type")
    private String operationType;

    /**
     * 操作描述
     */
    @TableField("operation_description")
    private String operationDescription;

    /**
     * 操作结果
     * <p>0: 失败
     * <p>1: 成功
     */
    @TableField("operation_result")
    private Byte operationResult;

    /**
     * 操作时间
     */
    @TableField("operation_time")
    private LocalDateTime operationTime;

    /**
     * 变动值
     */
    @TableField("change_value")
    private String changeValue;

    /**
     * 请求路径
     */
    @TableField("operation_path")
    private String operationPath;

    /**
     * 请求参数
     */
    @TableField("request_parameter")
    private String requestParameter;

    /**
     * 响应参数
     */
    @TableField("response_parameter")
    private String responseParameter;

    /**
     * 用户 IPv4 地址
     */
    @TableField("user_ipv4")
    private String userIpv4;

    /**
     * 用户 IPv4 地址整型值
     */
    @TableField("user_ipv4_decimal")
    private Object userIpv4Decimal;

    /**
     * 操作系统名称
     */
    @TableField("operating_system")
    private String operatingSystem;

    /**
     * 访问设备类型
     */
    @TableField("device_type")
    private String deviceType;

    /**
     * 浏览器信息（名称、版本）
     */
    @TableField("browser_info")
    private String browserInfo;

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