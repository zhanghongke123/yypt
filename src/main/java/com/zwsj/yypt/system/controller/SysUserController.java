package com.zwsj.yypt.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zwsj.yypt.common.domain.EleTreeData;
import com.zwsj.yypt.common.domain.QueryRequest;
import com.zwsj.yypt.common.domain.YyptResponse;
import com.zwsj.yypt.common.utils.TreeUtil;
import com.zwsj.yypt.system.domain.SysDept;
import com.zwsj.yypt.system.domain.SysUser;
import com.zwsj.yypt.system.service.SysDeptService;
import com.zwsj.yypt.system.service.SysUserService;
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

//    @PostMapping("")
//    public YyptResponse add(@RequestBody SysUser sysUser){
//        SysUser user = sysUserService.addUser(sysUser);
//        return YyptResponse.success(user);
//    }
}
