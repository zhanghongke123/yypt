package com.zwsj.yypt.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
@TableName("user")
@Data
public class SysUser {


    @TableId(value = "user_id",type = IdType.AUTO)
    private Long userId;

    @TableField("user_name")
    @NotBlank
    private String userName;

    @TableField("user_password")
    private String userPassword;

    @TableField("avatar")
    private String avatar;

    @TableField("mobile")
    private String mobile;

    @TableField("create_date")
    private Date  createDate;

    @TableField("modify_date")
    private  Date modifyDate;


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
