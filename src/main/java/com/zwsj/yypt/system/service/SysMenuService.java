package com.zwsj.yypt.system.service;

import com.zwsj.yypt.common.service.IService;
import com.zwsj.yypt.system.domain.SysMenu;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> getMenusByRoleId(Long roleId);

}
