package com.zwsj.yypt.system.controller;

import com.zwsj.yypt.common.domain.YyptResponse;
import com.zwsj.yypt.system.domain.SysMenu;
import com.zwsj.yypt.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("")
    public YyptResponse getMenuTree(){
       List<SysMenu> menutree = sysMenuService.getMenutreeView();
       return YyptResponse.success(menutree);
    }
}
