package com.yypt.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yypt.common.domain.QueryRequest;
import com.yypt.system.domain.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yypt.system.domain.SysLog;

/**
 * @author ZHK
 */
public interface SysDictService extends IService<SysDict> {

    IPage<SysDict> list(QueryRequest<SysDict> request);
}
