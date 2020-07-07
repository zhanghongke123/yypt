package com.yypt.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.properties.YyptConstant;
import com.yypt.common.utils.SortUtil;
import com.yypt.system.domain.SysDictDtl;
import com.yypt.system.dao.SysDictDtlMapper;
import com.yypt.system.service.SysDictDtlService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ZHK
 */
@Service
public class SysDictDtlServiceImpl extends ServiceImpl<SysDictDtlMapper, SysDictDtl> implements SysDictDtlService {

    @Override
    public IPage<SysDictDtl> list(QueryRequest<SysDictDtl> request) {
        Page<SysDictDtl> page = new Page<>();
        SortUtil.handlePageSort(request,page,"dictDtlId", YyptConstant.ORDER_DESC,true);
        return this.baseMapper.list(page,request.getQuerylist());
    }
}
