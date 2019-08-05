package com.zwsj.yypt.system.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zwsj.yypt.common.domain.QueryRequest;
import com.zwsj.yypt.system.domain.SysMenu;
import com.zwsj.yypt.system.domain.SysRole;
import com.zwsj.yypt.system.domain.SysRoleUser;
import com.zwsj.yypt.system.domain.SysUser;

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
