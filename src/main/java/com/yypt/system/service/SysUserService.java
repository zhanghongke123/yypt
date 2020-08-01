package com.yypt.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yypt.common.domain.QueryRequest;
import com.yypt.system.domain.SysRole;
import com.yypt.system.domain.SysUser;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */

public interface SysUserService extends IService<SysUser> {

    // 用户列表
    IPage<SysUser> list(QueryRequest<SysUser> request);



    SysUser findByName(String userName);

    /**
     * 通过用户ID获取角色信息
     * @param userId
     * @return
     */
    List<SysRole> getUserRoles(Long userId);


    /**
     * 修改或添加用户
     * @param sysUser
     * @return
     */
    SysUser updateOrAdd(SysUser sysUser) throws Exception;

    /**
     * 删除用户
     * @param sysUser
     */
    void del(SysUser sysUser);




}
