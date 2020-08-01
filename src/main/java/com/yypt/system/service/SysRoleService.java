package com.yypt.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yypt.common.domain.QueryRequest;
import com.yypt.system.domain.SysRole;
import com.yypt.system.domain.SysRoleUser;
import com.yypt.system.domain.SysUser;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
public interface SysRoleService extends IService<SysRole> {
    List<SysRole> getRoleByUserId(Long userId);
    List<SysRole> list(SysRole sysRole);
    List<SysRoleUser> getRoleUsers(SysRole sysRole);
    SysRole updateOrAdd(SysRole sysRole);
    void del(SysRole sysRole) throws Exception;
    void delRoleUser(SysRoleUser sysRoleUser) throws Exception;
    // 授权人员列表
    IPage<SysUser> userRep(QueryRequest<SysUser> request);

    void saverRoleUser(Long roleId, String userIds);

}
