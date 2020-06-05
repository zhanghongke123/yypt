package com.yypt.system.controller;

import com.yypt.common.annotation.Log;
import com.yypt.common.domain.YyptResponse;
import com.yypt.common.enums.ResultEnum;
import com.yypt.system.domain.SysDept;
import com.yypt.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 zhk
 * @创建时间 2019-07-12
 * @描述
 */
@RestController
@RequestMapping("sysdept")
public class SysDeptController {

    @Autowired
    SysDeptService sysDeptService;

    @RequestMapping("")
//    @Limit(name = "获取部门数", key = "dept", period = 50, count = 1, limitType = LimitType.Token)
    @Log("获取部门列表")
    public YyptResponse list(@RequestBody  SysDept sysDept){
       return YyptResponse.success(sysDeptService.getDeptTreeView(sysDept));
    }

    @RequestMapping("save")
    public YyptResponse save(@RequestBody SysDept sysDept){
      return YyptResponse.success(sysDeptService.updateOrAdd(sysDept));
    }


    @RequestMapping("del")
    public YyptResponse del(@RequestBody SysDept sysDept) throws Exception{
        Long deptId = sysDept.getDeptId();
        if(deptId == null){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"部门ID不能为空");
        }
        sysDeptService.delte(deptId);
        return YyptResponse.success("删除成功");
    }
}
