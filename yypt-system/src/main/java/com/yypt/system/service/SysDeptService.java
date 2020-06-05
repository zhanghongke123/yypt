package com.yypt.system.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yypt.system.domain.SysDept;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-07-12
 * @描述
 */
public interface SysDeptService extends IService<SysDept> {
    List<SysDept> getDeptTreeView(SysDept sysDept);

    SysDept updateOrAdd(SysDept sysDept);


    void delte(Long deptId) throws Exception;
}
