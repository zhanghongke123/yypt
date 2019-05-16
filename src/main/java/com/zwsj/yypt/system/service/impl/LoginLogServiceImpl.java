package com.zwsj.yypt.system.service.impl;


import com.zwsj.yypt.common.service.impl.BaseService;
import com.zwsj.yypt.common.utils.AddressUtil;
import com.zwsj.yypt.common.utils.HttpContextUtil;
import com.zwsj.yypt.common.utils.IPUtil;
import com.zwsj.yypt.system.domain.LoginLog;
import com.zwsj.yypt.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoginLogServiceImpl extends BaseService<LoginLog> implements LoginLogService {


    @Override
    @Transactional
    public void saveLoginLog(String userName) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserName(userName);
        loginLog.setLoginTime(new Date());
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(ip));
        this.save(loginLog);
    }
}
