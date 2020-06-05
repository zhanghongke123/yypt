package com.yypt.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.domain.YyptResponse;
import com.yypt.system.domain.SysLoginLog;
import com.yypt.system.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/sysloginlog")
@RestController
public class SysLoginLogController {
    @Autowired
    SysLoginLogService sysLoginLogService;

    @RequestMapping("/list")
    public YyptResponse list(@RequestBody QueryRequest<SysLoginLog> request){
        IPage<SysLoginLog> sysLoginLogPage = sysLoginLogService.list(request);
        return YyptResponse.success(sysLoginLogPage);
    }
}
