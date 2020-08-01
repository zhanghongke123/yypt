package com.yypt.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.domain.YyptResponse;
import com.yypt.system.domain.SysDict;
import com.yypt.system.domain.SysLog;
import com.yypt.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ZHK
 */
@RestController
@RequestMapping("/sysdict")
public class SysDictController {

    @Autowired
    SysDictService sysDictService;

    @RequestMapping("/list")
    public YyptResponse list(@RequestBody QueryRequest<SysDict> request){
        IPage<SysDict> sysDictPage = sysDictService.list(request);
        return YyptResponse.success(sysDictPage);
    }



    @RequestMapping("/save")
    public YyptResponse save(@RequestBody SysDict request){
        if(request.getDictId() == null){
           //新增
            request.setCreateTime(new Date());
        }else{
            request.setUpdateTime(new Date());
        }
        sysDictService.saveOrUpdate(request);
        return YyptResponse.success(request);
    }


    @RequestMapping("/delete")
    public YyptResponse delete(@RequestBody SysDict request){
        sysDictService.removeById(request.getDictId());
        return YyptResponse.success("删除成功");
    }


}
