package com.yypt.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yypt.common.domain.QueryRequest;
import com.yypt.system.domain.SysLoginLog;

/**
 * @创建人 zhk
 * @创建时间 2019-03-11
 * @描述
 */
public interface SysLoginLogService extends IService<SysLoginLog> {
    void saveLoginLog(String userAccount);

    IPage<SysLoginLog> list(QueryRequest<SysLoginLog> request);
}
