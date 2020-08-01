package com.yypt.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yypt.system.domain.SysDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yypt.system.domain.SysLog;
import com.yypt.system.domain.SysLoginLog;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZHK
 */
public interface SysDictMapper extends BaseMapper<SysDict> {

    IPage<SysDict> list(Page<SysDict> page, @Param("query") SysDict querylist);
}
