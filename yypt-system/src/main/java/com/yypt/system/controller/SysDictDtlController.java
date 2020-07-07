package com.yypt.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.domain.YyptResponse;
import com.yypt.system.domain.SysDict;
import com.yypt.system.domain.SysDictDtl;
import com.yypt.system.service.SysDictDtlService;
import com.yypt.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ZHK
 */
@RestController
@RequestMapping("/sysdictdtl")
public class SysDictDtlController {

    @Autowired
    SysDictDtlService sysDictDtlService;

    @RequestMapping("/list")
    public YyptResponse list(@RequestBody QueryRequest<SysDictDtl> request){
        IPage<SysDictDtl> sysDictDtlIPage = sysDictDtlService.list(request);
        return YyptResponse.success(sysDictDtlIPage);
    }



    @RequestMapping("/save")
    public YyptResponse save(@RequestBody SysDictDtl request){
        if(request.getDictDtlId() == null){
            //新增
            request.setCreateTime(new Date());
        }else{
            request.setUpdateTime(new Date());
        }
        sysDictDtlService.saveOrUpdate(request);
        return YyptResponse.success(request);
    }


    @RequestMapping("/delete")
    public YyptResponse delete(@RequestBody SysDictDtl request){
        sysDictDtlService.removeById(request.getDictDtlId());
        return YyptResponse.success("删除成功");
    }
}
