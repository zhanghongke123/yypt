package com.yypt.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yypt.system.domain.SysJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-05-20
 * @描述
 */
@Mapper
public interface SysJobMapper extends BaseMapper<SysJob> {
    List<SysJob> getAllJob(HashMap param);
}
