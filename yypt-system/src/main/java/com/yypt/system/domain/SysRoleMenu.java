package com.yypt.system.domain;

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
@TableName("sys_role_menu")
@Data
public class SysRoleMenu {

    @TableId(value = "role_menu_id",type = IdType.AUTO)
    private Long roleMenuId;


    @TableField("role_id")
    private Long roleId;


    @TableField("menu_id")
    private Long menuId;


    @TableField("create_date")
    private Date createDate;
}
