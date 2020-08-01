package com.yypt.system.domain;

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
@TableName("sys_menu_button")
@Data
public class SysMenuButton {

    @TableId(value = "button_id", type = IdType.AUTO)
    private Long buttonId;


    @TableField("name")
    private String name;


    @TableField("request_path")
    private String requestPath;

    @TableField("icon")
    private String icon;

    @TableField("order_code")
    public  Long orderCode;

    @TableField("menu_id")
    private Long menuId;

    @TableField("permission")
    private String permission;

    @TableField("create_date")
    private Date createDate;

    @TableField("modify_date")
    private Date modifyDate;
}
