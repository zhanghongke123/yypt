package com.yypt.system.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yypt.system.domain.SysRole;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> getUserRoles(Long userId);

    List<SysRole> list(SysRole sysRole);



}
