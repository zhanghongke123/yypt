package com.yypt.system.controller;


import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.domain.YyptResponse;
import com.yypt.system.domain.SysTenant;
import com.yypt.system.service.SysTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author ZHK
 */
@RestController
@RequestMapping("/system/sys-tenant")
public class SysTenantController {

    @Autowired
    SysTenantService sysTenantService;


    @RequestMapping("list")
    public YyptResponse list(@RequestBody QueryRequest<SysTenant> queryRequest){
        Page<Map> mapPage = sysTenantService.pageMap(queryRequest);
        return YyptResponse.success(mapPage);
    }



    @RequestMapping("save")
    public YyptResponse save(@RequestBody SysTenant sysTenant){
        Long tenantId = sysTenant.getTenantId();
        if (tenantId != null) {
            sysTenant.setUpdateTime(LocalDateTime.now());
        }else{
            sysTenant.setCreateTime(LocalDateTime.now());
        }
        sysTenantService.saveOrUpdate(sysTenant);
        return YyptResponse.success(sysTenant);
    }



    @RequestMapping("batchDelete")
    public YyptResponse batchDelete(@RequestBody List<SysTenant> sysTenantList){
        sysTenantService.removeByIds(sysTenantList);
        return YyptResponse.success("保存成功");
    }

    @RequestMapping("all")
    public YyptResponse all(){
        List<SysTenant> sysTenants = sysTenantService.list(Wrappers.<SysTenant>lambdaQuery().select(SysTenant::getTenantId, SysTenant::getTenantName));
        return YyptResponse.success(sysTenants);
    }

}
