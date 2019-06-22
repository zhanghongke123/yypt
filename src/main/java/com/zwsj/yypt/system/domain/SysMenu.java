package com.zwsj.yypt.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
@TableName("menu")
@Data
public class SysMenu {

    @TableId(value = "menu_id",type=IdType.AUTO )
    private Long menuId;


    @TableField("path")
    private String path;


    @TableField("component")
    private String component;


    @TableField("parentid")
    private Long parentid;

    @TableField("order_code")
    private Long orderCode;

    @TableField("name")
    private String name;


    @TableField("icon")
    private String icon;

    @TableField("permission")
    private String permission;


    // 1 为菜单  2 为按钮
    @TableField("type")
    private Long type;



    @TableField("create_date")
    private Date createDate;

    @TableField("modify_date")
    private  Date modifyDate;



    private transient List<SysMenu> children;


    public void initChildren(){
        this.children = new ArrayList<SysMenu>();
    }
}
