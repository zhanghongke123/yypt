package com.yypt.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yypt.common.domain.QueryRequest;
import com.yypt.system.domain.SysTenant;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author ZHK
 */
public interface SysTenantService extends IService<SysTenant> {

    Page<Map> pageMap(QueryRequest<SysTenant> queryRequest);
}
