package com.yypt.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.utils.SortUtil;
import com.yypt.system.domain.SysDictDtl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author ZHK
 */
public interface SysDictDtlService extends IService<SysDictDtl> {

    IPage<SysDictDtl> list(QueryRequest<SysDictDtl> request);


}
