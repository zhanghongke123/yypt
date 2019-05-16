package com.zwsj.yypt.system.service.impl;

import com.zwsj.yypt.common.service.impl.BaseService;
import com.zwsj.yypt.system.dao.SysMenuMapper;
import com.zwsj.yypt.system.domain.SysMenu;
import com.zwsj.yypt.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
@Service
public class SysMenuServiceImpl extends BaseService<SysMenu> implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> getMenusByRoleId(Long roleId) {
        return sysMenuMapper.getMenusByRoleId(roleId);
    }


    



}
