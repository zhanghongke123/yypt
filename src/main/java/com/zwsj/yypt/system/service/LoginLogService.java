package com.zwsj.yypt.system.service;

import com.zwsj.yypt.common.service.IService;
import com.zwsj.yypt.system.domain.LoginLog;
import org.springframework.stereotype.Service;

/**
 * @创建人 zhk
 * @创建时间 2019-03-11
 * @描述
 */
public interface LoginLogService extends IService<LoginLog> {
    void saveLoginLog(String userAccount);
}
