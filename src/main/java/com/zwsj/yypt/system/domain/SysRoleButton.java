package com.zwsj.yypt.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @创建人 zhk
 * @创建时间 2019-07-08
 * @描述
 */
@TableName("sys_role_button")
@Data
public class SysRoleButton {


    @TableId(value = "role_button_id",type = IdType.AUTO)
    private Long roleButtonId;


    @TableField("role_id")
    private Long roleId;

    @TableField("menu_id")
    private Long menuId;

    @TableField("button_id")
    private Long buttonId;

    @TableField("create_date")
    private Date createDate;
}
