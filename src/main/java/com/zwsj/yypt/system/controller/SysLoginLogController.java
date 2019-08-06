package com.zwsj.yypt.system.controller;


import com.zwsj.yypt.common.domain.YyptResponse;
import com.zwsj.yypt.system.domain.LoginLog;
import com.zwsj.yypt.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RequestMapping("/sysloginlog")
@RestController
public class SysLoginLogController {
    @Autowired
    LoginLogService loginLogService;

    @RequestMapping("")
    public YyptResponse list(LoginLog loginLog){
        List<LoginLog> loginLogList = loginLogService.list();
        return YyptResponse.success(loginLogList);
    }
}
