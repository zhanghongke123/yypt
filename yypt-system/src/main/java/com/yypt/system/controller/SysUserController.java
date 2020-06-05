package com.yypt.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yypt.common.domain.EleTreeData;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.domain.YyptResponse;
import com.yypt.common.enums.ResultEnum;
import com.yypt.common.utils.MD5Utils;
import com.yypt.common.utils.TreeUtil;
import com.yypt.system.domain.SysDept;
import com.yypt.system.domain.SysUser;
import com.yypt.system.service.SysDeptService;
import com.yypt.system.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    SysDeptService sysDeptService;

    @PostMapping("")
    public YyptResponse list(@RequestBody QueryRequest<SysUser> request){
        IPage<SysUser> sysUserPage = sysUserService.list(request);
        return YyptResponse.success(sysUserPage);
    }


    @PostMapping("/depttree")
    public YyptResponse deptTreeData(){
        List<SysDept> depts = sysDeptService.list();
        List<EleTreeData> depttree =  TreeUtil.buildElemTree(depts,"deptId","{deptName}({deptId})",
                "parentid");
        return YyptResponse.success(depttree);
    }

    @PostMapping("/save")
    public YyptResponse save(@RequestBody SysUser sysUser) throws Exception{
        sysUserService.updateOrAdd(sysUser);
        return YyptResponse.success("保存成功");
    }


    @PostMapping("/del")
    public YyptResponse del(@RequestBody SysUser sysUser){
        sysUserService.del(sysUser);
        return YyptResponse.success("删除成功");
    }


    @PostMapping("/updatePwd")
    public YyptResponse updatePwd(@RequestBody JSONObject jsonObject) throws Exception {
        String  username = jsonObject.getString("username");
        String oldPwd = jsonObject.getString("oldPwd");
        String newPwd1 = jsonObject.getString("newPwd1");
        String md5password = MD5Utils.encrypt(username,oldPwd);

        SysUser sysUser = sysUserService.findByName(username);
        if(sysUser == null){
            return YyptResponse.failure(ResultEnum.USER_NOTEXIST);
        }

        if(!md5password.equals(sysUser.getUserPassword())){
            return  YyptResponse.failure("原密码不正确");
        }

        if(StringUtils.isEmpty(newPwd1)){
            return  YyptResponse.failure("密码不能为空");
        }

        String newMd5Password = MD5Utils.encrypt(username,newPwd1);
        sysUser.setUserPassword(newMd5Password);
        sysUserService.updateOrAdd(sysUser);

        return YyptResponse.success("更新成功");
    }

//    @PostMapping("")
//    public YyptResponse add(@RequestBody SysUser sysUser){
//        SysUser user = sysUserService.addUser(sysUser);
//        return YyptResponse.success(user);
//    }
}
