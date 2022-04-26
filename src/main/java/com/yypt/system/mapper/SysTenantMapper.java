package com.yypt.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yypt.system.domain.SysTenant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author ZHK
 */
public interface SysTenantMapper extends BaseMapper<SysTenant> {

    Page<Map> pageMap(Page page, @Param(Constants.WRAPPER)QueryWrapper queryWrapper);
}
