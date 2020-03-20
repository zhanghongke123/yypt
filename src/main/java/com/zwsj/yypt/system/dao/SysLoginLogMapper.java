package com.zwsj.yypt.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwsj.yypt.system.domain.SysLoginLog;
import com.zwsj.yypt.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @创建人 zhk
 * @创建时间 2019-04-06
 * @描述
 */
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {
    Page<SysLoginLog> list(Page page, @Param("sysLoginLog") SysLoginLog sysLoginLog);

}
