package com.zwsj.yypt.system.domain;

import lombok.Data;

import javax.annotation.PropertyKey;
import javax.management.relation.RoleList;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
@Table(name = "user")
@Data
public class SysUser {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    @NotBlank
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "create_date")
    private Date  createDate;

    @Column(name = "modify_date")
    private  Date modifyDate;


    @Transient
    private List<SysRole> roleList;


    @Transient
    private String roleNames;


    @Transient
    private String roleIds;


    // 排序字段
    @Transient
    private String sortField;


    // 排序规则 ascend 升序 descend 降序
    @Transient
    private String sortOrder;


    @Transient
    private String id;


    /**
     * shiro-redis v3.1.0 必须要有 getAuthCacheKey()或者 getId()方法
     * # Principal id field name. The field which you can get unique id to identify this principal.
     * # For example, if you use UserInfo as Principal class, the id field maybe userId, userName, email, etc.
     * # Remember to add getter to this id field. For example, getUserId(), getUserName(), getEmail(), etc.
     * # Default value is authCacheKey or id, that means your principal object has a method called "getAuthCacheKey()" or "getId()"
     *
     * @return userId as Principal id field name
     */
    public Long getAuthCacheKey() {
        return userId;
    }




}
