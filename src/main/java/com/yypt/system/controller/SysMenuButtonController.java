package com.yypt.system.controller;

import com.yypt.common.domain.YyptResponse;
import com.yypt.common.enums.ResultEnum;
import com.yypt.system.domain.SysMenuButton;
import com.yypt.system.service.SysMenuButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @创建人 zhk
 * @创建时间 2019-07-08
 * @描述
 */
@RestController
@RequestMapping("sysmenubutton")
public class SysMenuButtonController {

    @Autowired
    SysMenuButtonService sysMenuButtonService;

    @RequestMapping("save")
    public YyptResponse save(@RequestBody  SysMenuButton sysMenuButton){
        Long buttonId = sysMenuButton.getButtonId();
        sysMenuButton.setModifyDate(new Date());
        if( buttonId == null){
            sysMenuButton.setCreateDate(new Date());
            sysMenuButtonService.save(sysMenuButton);
        }else {
            sysMenuButtonService.updateById(sysMenuButton);
        }
        return YyptResponse.success(sysMenuButton);
    }

    @RequestMapping("del")
    public YyptResponse del(@RequestBody SysMenuButton sysMenuButton) throws Exception{
        Long buttonId = sysMenuButton.getButtonId();
        if(buttonId == null){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"buttonId不能为空");
        }
        sysMenuButtonService.delete(buttonId);
        return YyptResponse.success("删除成功");
    }

}
