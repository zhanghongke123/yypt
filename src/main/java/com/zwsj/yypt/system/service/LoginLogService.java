package com.zwsj.yypt.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwsj.yypt.system.domain.LoginLog;

/**
 * @创建人 zhk
 * @创建时间 2019-03-11
 * @描述
 */
public interface LoginLogService extends IService<LoginLog> {
    void saveLoginLog(String userAccount);
}
