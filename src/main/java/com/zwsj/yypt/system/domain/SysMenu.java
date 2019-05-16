package com.zwsj.yypt.system.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
@Table(name = "menu")
@Data
public class SysMenu {
    @Id
    @Column(name = "menu_id")
    @GeneratedValue(generator = "JDBC")
    private Long menuId;


    @Column(name = "path")
    private String path;


    @Column(name = "component")
    private String component;


    @Column(name = "parentid")
    private String parentid;

    @Column(name="order_code")
    private Long orderCode;

    @Column(name = "name")
    private String name;


    @Column(name = "icon")
    private String icon;

    @Column(name = "permission")
    private String permission;



    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modify_date")
    private  Date modifyDate;
}
