package com.yypt.system.controller;

import com.yypt.common.domain.YyptResponse;
import com.yypt.common.enums.ResultEnum;
import com.yypt.system.domain.SysMenu;
import com.yypt.system.domain.SysMenuButton;
import com.yypt.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-06-19
 * @描述
 */
@RestController
@RequestMapping("sysmenu")
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;


    @PostMapping("")
    public YyptResponse getMenuTree(){
       List<SysMenu> menutree = sysMenuService.getMenutreeView();
       return YyptResponse.success(menutree);
    }

    @PostMapping("/save")
    public YyptResponse save(@RequestBody SysMenu sysMenu){
        SysMenu resp = sysMenuService.updateOrAdd(sysMenu);
        return YyptResponse.success(resp);
    }

    @PostMapping("/delete")
    public YyptResponse delete(@RequestBody SysMenu sysMenu) {
        Long menuId = sysMenu.getMenuId();
        if(menuId == null){
            return YyptResponse.failure(ResultEnum.PASSWORD_ERROR,"ID不能为空");
        }
        sysMenuService.deleteMenu(sysMenu);
        return YyptResponse.success("删除成功");
    }

    @RequestMapping("/getMenuButtons")
    public YyptResponse getMenuButtons(@RequestBody SysMenu sysMenu){
        List<SysMenuButton> res = sysMenuService.getMenuButtons(sysMenu);
        return YyptResponse.success(res);
    }
}
