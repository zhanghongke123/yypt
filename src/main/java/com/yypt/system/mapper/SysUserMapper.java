package com.yypt.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yypt.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
public interface SysUserMapper extends BaseMapper<SysUser> {



    Page<SysUser> userRep(Page page, @Param("sysUser") SysUser sysUser);

    Page<Map> pageMap(Page page,  @Param(Constants.WRAPPER)QueryWrapper queryWrapper);
}
