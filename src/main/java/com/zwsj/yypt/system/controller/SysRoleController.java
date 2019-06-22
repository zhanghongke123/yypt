package com.zwsj.yypt.system.controller;

import com.zwsj.yypt.common.domain.YyptResponse;
import com.zwsj.yypt.system.dao.SysRoleMapper;
import com.zwsj.yypt.system.domain.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-07
 * @描述
 */
@RestController
@RequestMapping("role")
@Validated
public class SysRoleController {
    @Autowired
    SysRoleMapper sysRoleMapper;

//    @GetMapping("list")
//    public YyptResponse getAllRoles(){
//       List<SysRole> data =  sysRoleMapper.
//       return YyptResponse.success(data);
//    }
}
