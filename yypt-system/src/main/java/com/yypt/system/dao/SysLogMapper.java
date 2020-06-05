package com.yypt.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yypt.system.domain.SysLog;
import org.apache.ibatis.annotations.Param;

/**
 * @创建人 zhk
 * @创建时间 2019-08-09
 * @描述
 */
public interface SysLogMapper extends BaseMapper<SysLog> {
    IPage<SysLog> list(Page page, @Param("sysLog") SysLog sysLog);
}
