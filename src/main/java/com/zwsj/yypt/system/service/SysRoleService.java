package com.zwsj.yypt.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwsj.yypt.system.domain.SysMenu;
import com.zwsj.yypt.system.domain.SysRole;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
public interface SysRoleService extends IService<SysRole> {
    List<SysRole> getRoleByUserId(Long userId);
    List<SysRole> list(SysRole sysRole);
}
