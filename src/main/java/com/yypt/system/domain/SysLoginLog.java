package com.yypt.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@TableName("sys_login_log")
@ToString
@Data
public class SysLoginLog {

    @TableId(value = "login_id",type = IdType.AUTO)
    private Long loginId;

    /**
     * 用户 ID
     */
    @TableField("user_name")
    private String userName;

    /**
     * 登录时间
     */
    @TableField("LOGIN_TIME")
    private Date loginTime;

    /**
     * 登录地点
     */
    @TableField("LOCATION")
    private String location;

    @TableField("IP")
    private String ip;
}