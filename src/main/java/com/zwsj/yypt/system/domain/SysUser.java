package com.zwsj.yypt.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.extern.java.Log;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
@TableName("sys_user")
@Data
public class SysUser {


    @TableId(value = "user_id",type = IdType.AUTO)
    private Long userId;

    /**
     * 账号名称（用来登录）
     */
    @TableField("user_name")
    @NotBlank
    private String userName;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    @TableField("user_password")
    private String userPassword;

    @TableField("avatar")
    private String avatar;

    @TableField("mobile")
    private String mobile;

    @TableField("dept_id")
    private Long deptId;

    /**
     * 部门名称
     */
    private transient String deptName;

    /**
     * 身份证号
     */
    @TableField("idcard")
    private String idcard;

    /**
     * 用来微信绑定
     */
    @TableField("openid")
    private String openid;


    /**
     * 人员状态 0停用  1正常  2 锁定
     */
    @TableField("status")
    private Long status;

    @TableField("create_date")
    private Date  createDate;

    @TableField("modify_date")
    private  Date modifyDate;

    /**
     * 锁定日期
     */
    @TableField("lock_date")
    private Date lockDate;
    private transient List<SysRole> roleList;


    private transient String roleNames;


    private transient String roleIds;


    // 排序字段
    private transient String sortField;


    // 排序规则 ascend 升序 descend 降序
    private transient String sortOrder;


    private  transient String id;


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
