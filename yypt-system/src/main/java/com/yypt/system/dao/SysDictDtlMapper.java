package com.yypt.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yypt.system.domain.SysDictDtl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZHK
 */
public interface SysDictDtlMapper extends BaseMapper<SysDictDtl> {

    IPage<SysDictDtl> list(Page<SysDictDtl> page, @Param("query")SysDictDtl querylist);
}
