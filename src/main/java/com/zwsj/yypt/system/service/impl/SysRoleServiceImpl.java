package com.zwsj.yypt.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwsj.yypt.system.dao.SysRoleMapper;
import com.zwsj.yypt.system.domain.SysMenu;
import com.zwsj.yypt.system.domain.SysRole;
import com.zwsj.yypt.system.service.SysMenuService;
import com.zwsj.yypt.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;


    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        return sysRoleMapper.getUserRoles(userId);
    }

    @Override
    public List<SysRole> list(SysRole sysRole) {
        List<SysRole> data = sysRoleMapper.list(sysRole);
        return data;
    }


}
