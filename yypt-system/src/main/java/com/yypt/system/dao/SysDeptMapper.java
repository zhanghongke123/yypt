package com.yypt.system.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yypt.system.domain.SysDept;

/**
 * @创建人 zhk
 * @创建时间 2019-07-12
 * @描述
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {
    void deleteBy(Long deptId);
}
