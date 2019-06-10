package com.zwsj.yypt.system.service;

import com.zwsj.yypt.common.domain.QueryRequest;
import com.zwsj.yypt.common.service.IService;
import com.zwsj.yypt.system.dao.SysRoleMapper;
import com.zwsj.yypt.system.domain.SysRole;
import com.zwsj.yypt.system.domain.SysUser;
import org.apache.catalina.User;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */

public interface SysUserService extends IService<SysUser> {


    SysUser findByName(String userName);

    /**
     * 通过用户ID获取角色信息
     * @param userId
     * @return
     */
    List<SysRole> getUserRoles(Long  userId);



    List<SysUser> list(SysUser sysUser,QueryRequest request);


    SysUser addUser(SysUser sysUser);




}
