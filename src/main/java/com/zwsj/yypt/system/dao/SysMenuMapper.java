package com.zwsj.yypt.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwsj.yypt.system.domain.SysMenu;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> getMenusByRoleId(Long roleId);

    List<SysMenu> getAllMenu();

    void deleteMenu(Long menuId);

}
