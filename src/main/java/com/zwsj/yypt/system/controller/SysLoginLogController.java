package com.zwsj.yypt.system.controller;


import com.zwsj.yypt.common.domain.YyptResponse;
import com.zwsj.yypt.system.domain.SysLoginLog;
import com.zwsj.yypt.system.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sysloginlog")
@RestController
public class SysLoginLogController {
    @Autowired
    SysLoginLogService sysLoginLogService;

    @RequestMapping("")
    public YyptResponse list(SysLoginLog sysLoginLog){
        List<SysLoginLog> sysLoginLogList = sysLoginLogService.list();
        return YyptResponse.success(sysLoginLogList);
    }
}
