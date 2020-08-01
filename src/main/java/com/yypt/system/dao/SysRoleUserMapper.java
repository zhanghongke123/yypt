package com.yypt.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yypt.system.domain.SysRoleUser;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {
    List<SysRoleUser> getRoleUsers(SysRoleUser sysRoleUser);
}
