package com.zwsj.yypt.system.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "role_menu")
@Data
public class SysRoleMenu {

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "role_menu_id")
    private Long roleMenuId;


    @Column(name = "role_id")
    private Long roleId;


    @Column(name = "menu_id")
    private Long menuId;


    @Column(name = "create_date")
    private Date createDate;
}
