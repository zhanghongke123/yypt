package com.zwsj.yypt.system.domain;

import lombok.Data;
import lombok.ToString;

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
@Table(name = "role")
@Data
@ToString
public class SysRole {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(generator = "JDBC")
    private Long roleId;


    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_code")
    private String roleCode;


    @Column(name = "create_date")
    private Date  createDate;

    @Column(name = "modify_date")
    private  Date modifyDate;
}
