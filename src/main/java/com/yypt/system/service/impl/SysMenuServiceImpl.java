package com.yypt.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yypt.system.dao.SysMenuButtonMapper;
import com.yypt.system.dao.SysMenuMapper;
import com.yypt.system.dao.SysRoleMenuMapper;
import com.yypt.system.domain.SysDept;
import com.yypt.system.domain.SysMenu;
import com.yypt.system.domain.SysMenuButton;
import com.yypt.system.domain.SysRoleMenu;
import com.yypt.system.service.SysMenuService;
import net.sf.saxon.expr.instruct.ForEach;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper,SysMenu> implements SysMenuService {

    List<SysMenu> childrenMenus;

    @Autowired
    SysMenuMapper sysMenuMapper;


    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    SysMenuButtonMapper sysMenuButtonMapper;


    @Override
    public List<SysMenu> getMenusByRoleId(Long roleId) {
        List<SysMenu> res = sysMenuMapper.getMenusByRoleId(roleId);
        return res;
    }

    @Override
    public List<SysMenu> getMenutreeView() {
        List<SysMenu> menus =  sysMenuMapper.getAllMenu();
        List<SysMenu> topRoutes = new ArrayList<SysMenu>();
        menus.forEach(menu -> {
            if(menu.getMenuId() == 0L){
                topRoutes.add(menu);
                return;
            }

            Long parentId = menu.getParentid();
            if (parentId == null || 0L == parentId) {
                topRoutes.add(menu);
                return;
            }
            for (SysMenu parent : menus) {
                Long menuId = parent.getMenuId();
                if (menuId != null  &&  menuId == parentId) {
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

    @Override
    public List<SysMenu> getlist() {
        return sysMenuMapper.getAllMenu();
    }

    @Override
    public List<SysMenuButton> getMenuButtons(SysMenu sysMenu) {
        //获取该菜单下面所对应的按钮
        Long menuId = sysMenu.getMenuId();
        LambdaQueryWrapper<SysMenuButton> condition = new LambdaQueryWrapper<>();
        condition.eq(SysMenuButton::getMenuId,menuId);
        return sysMenuButtonMapper.selectList(condition);
    }

    @Override
    public SysMenu updateOrAdd(SysMenu sysMenu) {
        //有ID的话就是修改，没有ID的话就是保存
        Long menuId = sysMenu.getMenuId();
        if(menuId != null){
            sysMenu.setModifyDate(new Date());
            this.baseMapper.updateById(sysMenu);
        }else{
            sysMenu.setModifyDate(new Date());
            sysMenu.setCreateDate(new Date());
            this.baseMapper.insert(sysMenu);
        }
        return sysMenu;
    }

    @Override
    public void deleteMenu(SysMenu sysMenu) throws Exception{
        //要删除下面的该节点，和该节点下面的所有子节点
        Long menuId = sysMenu.getMenuId();
        if(menuId == 0){
            throw  new Exception("顶级目录不可删除");
        }

        childrenMenus = new ArrayList<>();
        List<SysMenu> sysMenuList = this.list();
        List<SysMenu> childrenList = this.getAllChildren(sysMenuList,menuId);
        List<Long> childrenIds = childrenList.stream().map(dept -> dept.getMenuId()).collect(Collectors.toList());
        childrenIds.add(menuId);

        //删除对应的按钮
        for (Long menuid: childrenIds) {
            LambdaQueryWrapper<SysMenuButton> sysMenuButtonLambdaQueryWrapper = new LambdaQueryWrapper<>();
            sysMenuButtonLambdaQueryWrapper.eq(SysMenuButton::getMenuId,menuid);
            this.sysMenuButtonMapper.delete(sysMenuButtonLambdaQueryWrapper);
        }


        this.baseMapper.deleteBatchIds(childrenIds);
//        sysMenuMapper.deleteMenu(menuId);

    }

    @Override
    public void saveRoleMenu(Long roleId, String menuids) {
        //先删除角色对应的所有权限
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setRoleId(roleId);
        LambdaQueryWrapper<SysRoleMenu> condition = new LambdaQueryWrapper<>();
        condition.eq(SysRoleMenu::getRoleId,roleId);
        sysRoleMenuMapper.delete(condition);

        //重新新增角色人员
        String[] menuidarry = menuids.split(",");
        for(String menuid:menuidarry){
            if(StringUtils.isEmpty(menuid)){
                continue;
            }
            SysRoleMenu newsysRoleMenu = new SysRoleMenu();
            newsysRoleMenu.setRoleId(roleId);
            newsysRoleMenu.setMenuId(Long.valueOf(menuid));
            newsysRoleMenu.setCreateDate(new Date());
            sysRoleMenuMapper.insert(newsysRoleMenu);
        }

    }



    /**
     * 获取当前菜单下面的所有子菜单
     * @param menus
     * @param parentid
     * @return
     */
    public List<SysMenu> getAllChildren(List<SysMenu> menus, Long parentid){
        for (SysMenu sysMenu:menus) {
            if(sysMenu.getParentid() == parentid){
                getAllChildren(menus,sysMenu.getMenuId());
                childrenMenus.add(sysMenu);
            }
        }
        return childrenMenus;
    }


}
