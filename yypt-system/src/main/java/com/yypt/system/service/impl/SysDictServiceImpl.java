package com.yypt.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.properties.YyptConstant;
import com.yypt.common.utils.SortUtil;
import com.yypt.system.domain.SysDict;
import com.yypt.system.dao.SysDictMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yypt.system.domain.SysLog;
import com.yypt.system.domain.SysLoginLog;
import com.yypt.system.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ZHK
 */
@Service
@Slf4j
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Override
    public IPage<SysDict> list(QueryRequest<SysDict> request) {
        try {
            Page<SysDict> page = new Page<>();
            SortUtil.handlePageSort(request, page, "dictId", YyptConstant.ORDER_DESC,true);
            return this.baseMapper.list(page,request.getQuerylist());
        } catch (Exception e) {
            log.error("查询登录历史异常", e);
            return null;
        }

    }
}
