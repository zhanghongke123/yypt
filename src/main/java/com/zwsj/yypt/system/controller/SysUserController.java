package com.zwsj.yypt.system.controller;

import com.github.pagehelper.PageInfo;
import com.zwsj.yypt.common.controller.BaseController;
import com.zwsj.yypt.common.domain.QueryRequest;
import com.zwsj.yypt.common.domain.YyptResponse;
import com.zwsj.yypt.system.domain.SysUser;
import com.zwsj.yypt.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @创建人 zhk
 * @创建时间 2019-04-06
 * @描述
 */
@RestController
@RequestMapping("user")
@Validated
public class SysUserController extends BaseController {
    @Autowired
    private  SysUserService sysUserService;

    @GetMapping("list")
    public Map<String, Object> list(QueryRequest request, SysUser sysUser){
        Map<String, Object> data = super.selectByPageNumSize(request,() -> sysUserService.findUserDetail(sysUser,request));
        return YyptResponse.success(data);
    }

    @PostMapping("")
    public YyptResponse add(@RequestBody SysUser sysUser){
        SysUser user = sysUserService.addUser(sysUser);
        return YyptResponse.success(user);
    }
}
