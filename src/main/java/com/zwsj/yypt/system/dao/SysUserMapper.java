package com.zwsj.yypt.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwsj.yypt.system.domain.SysUser;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> list(SysUser sysUser);

}
