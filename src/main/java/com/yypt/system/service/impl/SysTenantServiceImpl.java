package com.yypt.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.properties.YyptConstant;
import com.yypt.common.utils.Condition;
import com.yypt.common.utils.SortUtil;
import com.yypt.system.domain.SysTenant;
import com.yypt.system.mapper.SysTenantMapper;
import com.yypt.system.service.SysTenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ZHK
 */
@Service
public class SysTenantServiceImpl extends ServiceImpl<SysTenantMapper, SysTenant> implements SysTenantService {

    @Override
    public Page<Map> pageMap(QueryRequest<SysTenant> queryRequest) {
        SysTenant querylist = queryRequest.getQuerylist();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (querylist != null) {
            queryWrapper = Condition.getQueryWrapper(queryRequest.getQuerylist(),false);
        }
        Page page = new Page();
        SortUtil.handlePageSort(queryRequest,page,"tenantId", YyptConstant.ORDER_DESC,true);
        return this.baseMapper.pageMap(page , queryWrapper);
    }
}
