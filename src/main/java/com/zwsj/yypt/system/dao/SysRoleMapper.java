package com.zwsj.yypt.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwsj.yypt.system.domain.SysRole;
import com.zwsj.yypt.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

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
