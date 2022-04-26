package com.yypt.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puppycrawl.tools.checkstyle.gui.MainFrame;
import com.yypt.common.domain.EleTreeData;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.domain.YyptResponse;
import com.yypt.common.enums.ResultEnum;
import com.yypt.common.utils.MD5Utils;
import com.yypt.common.utils.TreeUtil;
import com.yypt.system.domain.SysDept;
import com.yypt.system.domain.SysTenant;
import com.yypt.system.domain.SysUser;
import com.yypt.system.service.SysDeptService;
import com.yypt.system.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
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

    @Autowired
    SysDeptService sysDeptService;


    @RequestMapping("list")
    public YyptResponse list(@RequestBody QueryRequest<SysUser> queryRequest){
        Page<Map> mapPage = sysUserService.pageMap(queryRequest);
        return YyptResponse.success(mapPage);
    }


    @RequestMapping("save")
    public YyptResponse save(@RequestBody SysUser sysUser) throws Exception {
        sysUserService.updateOrAdd(sysUser);
        return YyptResponse.success(sysUser);
    }



    @RequestMapping("batchDelete")
    public YyptResponse batchDelete(@RequestBody List<SysUser> sysUserList){
        sysUserService.removeByIds(sysUserList);
        return YyptResponse.success("保存成功");
    }


    @PostMapping("/depttree")
    public YyptResponse deptTreeData(){
        List<SysDept> depts = sysDeptService.list();
        List<EleTreeData> depttree =  TreeUtil.buildElemTree(depts,"deptId","{deptName}({deptId})",
                "parentid");
        return YyptResponse.success(depttree);
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


    @PostMapping("/respassword")
    @Transactional(rollbackFor = Exception.class)
    public YyptResponse respassword(@RequestBody SysUser sysUser) throws Exception {
        String username = sysUser.getUserName();
        String password = sysUser.getUserPassword();
        if(StringUtils.isEmpty(username)){
            throw  new Exception("用户名不能为空");
        }

        if(StringUtils.isEmpty(password)){
            throw  new Exception("密码不能为空");
        }
        String md5password = MD5Utils.encrypt(username,password);
        sysUser.setUserPassword(md5password);
        LambdaUpdateWrapper<SysUser> sysuerWheres = new LambdaUpdateWrapper<>();
        sysuerWheres.eq(SysUser::getUserId,sysUser.getUserId());
        SysUser updateUser = new SysUser();
        updateUser.setUserPassword(md5password);
        sysUserService.update(updateUser,sysuerWheres);

        return YyptResponse.success("重置密码成功");
    }


}
