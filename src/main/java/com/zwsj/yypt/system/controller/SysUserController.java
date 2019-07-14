package com.zwsj.yypt.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwsj.yypt.common.controller.BaseController;
import com.zwsj.yypt.common.domain.QueryRequest;
import com.zwsj.yypt.common.domain.YyptResponse;
import com.zwsj.yypt.system.domain.SysUser;
import com.zwsj.yypt.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @创建人 zhk
 * @创建时间 2019-04-06
 * @描述
 */
@RestController
@RequestMapping("sysuser")
@Validated
public class SysUserController  {
    @Autowired
    private  SysUserService sysUserService;

    @PostMapping("")
    public YyptResponse list(@RequestBody QueryRequest<SysUser> request){
        IPage<SysUser> sysUserPage = sysUserService.list(request.getQuerylist(),request);
        return YyptResponse.success(sysUserPage);
    }

//    @PostMapping("")
//    public YyptResponse add(@RequestBody SysUser sysUser){
//        SysUser user = sysUserService.addUser(sysUser);
//        return YyptResponse.success(user);
//    }
}
