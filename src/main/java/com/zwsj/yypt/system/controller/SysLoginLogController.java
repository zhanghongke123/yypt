package com.zwsj.yypt.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zwsj.yypt.common.domain.QueryRequest;
import com.zwsj.yypt.common.domain.YyptResponse;
import com.zwsj.yypt.system.domain.SysLoginLog;
import com.zwsj.yypt.system.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
