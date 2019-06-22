package com.zwsj.yypt.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
@TableName("role")
@Data
@ToString
public class SysRole {

    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;


    @TableField("role_name")
    private String roleName;

    @TableField("role_code")
    private String roleCode;


    @TableField("create_date")
    private Date  createDate;

    @TableField("modify_date")
    private  Date modifyDate;
}
