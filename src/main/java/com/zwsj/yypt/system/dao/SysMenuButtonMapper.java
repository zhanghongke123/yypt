package com.zwsj.yypt.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwsj.yypt.system.domain.SysMenu;
import com.zwsj.yypt.system.domain.SysMenuButton;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-07-08
 * @描述
 */
public interface SysMenuButtonMapper extends BaseMapper<SysMenuButton> {
    List<SysMenuButton> getButtonByRoleId(Long roleId);
}
