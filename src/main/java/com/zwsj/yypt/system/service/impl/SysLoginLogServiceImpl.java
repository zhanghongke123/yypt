package com.zwsj.yypt.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwsj.yypt.common.utils.AddressUtil;
import com.zwsj.yypt.common.utils.HttpContextUtil;
import com.zwsj.yypt.common.utils.IPUtil;
import com.zwsj.yypt.system.dao.SysLoginLogMapper;
import com.zwsj.yypt.system.domain.SysLoginLog;
import com.zwsj.yypt.system.service.SysLoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @创建人 zhk
 * @创建时间 2019-03-11
 * @描述
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper,SysLoginLog> implements SysLoginLogService {


    @Override
    @Transactional
    public void saveLoginLog(String userName) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUserName(userName);
        sysLoginLog.setLoginTime(new Date());
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        sysLoginLog.setIp(ip);
        sysLoginLog.setLocation(AddressUtil.getCityInfo(ip));
        this.save(sysLoginLog);
    }

}
