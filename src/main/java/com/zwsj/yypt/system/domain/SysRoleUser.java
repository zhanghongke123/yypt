package com.zwsj.yypt.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
@TableName("sys_role_user")
@Data
public class SysRoleUser {

    @TableId(value = "role_user_id", type = IdType.AUTO)
    private Long roleUserId;

    @TableField("role_id")
    private Long roleId;

    @TableField("role_id")
    private Long userId;

    @TableField("role_id")
    private Date createDate;
}
