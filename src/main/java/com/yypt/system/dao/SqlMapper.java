package com.yypt.system.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
@Mapper
@DS("slave_1")
public interface SqlMapper  {
    //查询

    List<JSONObject> select(@Param("sql") String sql);

    //更新
    int update(@Param("sql") String sql);



}
