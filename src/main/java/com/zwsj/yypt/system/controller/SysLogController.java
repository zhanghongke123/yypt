package com.zwsj.yypt.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zwsj.yypt.common.domain.QueryRequest;
import com.zwsj.yypt.common.domain.YyptResponse;
import com.zwsj.yypt.system.domain.SysLog;
import com.zwsj.yypt.system.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/syslog")
@RestController
public class SysLogController {
    @Autowired
    SysLogService sysLogService;

    @RequestMapping("/list")
    public YyptResponse list(@RequestBody QueryRequest<SysLog> request){
        IPage<SysLog> sysLogPage = sysLogService.list(request);
        return YyptResponse.success(sysLogPage);
    }
}
