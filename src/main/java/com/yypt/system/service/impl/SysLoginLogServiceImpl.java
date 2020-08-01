package com.yypt.system.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.properties.YyptConstant;
import com.yypt.common.utils.AddressUtil;
import com.yypt.common.utils.HttpContextUtil;
import com.yypt.common.utils.IPUtil;
import com.yypt.common.utils.SortUtil;
import com.yypt.system.dao.SysLoginLogMapper;
import com.yypt.system.domain.SysLoginLog;
import com.yypt.system.service.SysLoginLogService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    @Override
    public IPage<SysLoginLog> list(QueryRequest<SysLoginLog> request) {
        try {
            Page<SysLoginLog> page = new Page<>();
            SortUtil.handlePageSort(request, page, "loginId",YyptConstant.ORDER_DESC,true);
            return this.baseMapper.list(page,request.getQuerylist());
        } catch (Exception e) {
            log.error("查询登录历史异常", e);
            return null;
        }
    }

}
