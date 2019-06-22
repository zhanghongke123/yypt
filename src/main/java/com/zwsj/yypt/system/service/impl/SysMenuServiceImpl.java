package com.zwsj.yypt.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwsj.yypt.common.domain.route.RouteData;
import com.zwsj.yypt.system.dao.LoginLogMapper;
import com.zwsj.yypt.system.dao.SysMenuMapper;
import com.zwsj.yypt.system.domain.LoginLog;
import com.zwsj.yypt.system.domain.SysMenu;
import com.zwsj.yypt.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper,SysMenu> implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> getMenusByRoleId(Long roleId) {
        return sysMenuMapper.getMenusByRoleId(roleId);
    }

    @Override
    public List<SysMenu> getMenutreeView() {
        List<SysMenu> menus =  sysMenuMapper.getAllMenu();
        List<SysMenu> topRoutes = new ArrayList<SysMenu>();
        menus.forEach(menu -> {

            Long parentId = menu.getParentid();
            if (parentId == null || 0L == parentId) {
                topRoutes.add(menu);
                return;
            }
            for (SysMenu parent : menus) {
                Long menuId = parent.getMenuId();
                if (menuId != null && menuId == parentId) {
                    if (parent.getChildren() == null){
                        parent.initChildren();
                    }
                    parent.getChildren().add(menu);
                    return;
                }
            }
        });
        return topRoutes;
    }


}
