package com.yypt.system.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yypt.system.domain.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @创建人 zhk
 * @创建时间 2019-07-12
 * @描述
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {
    Page<Map> pageMap(Page page, @Param(Constants.WRAPPER)QueryWrapper queryWrapper);
//    void deleteBy(Long deptId);
}
