package com.zwsj.yypt.system.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.jdbc.core.SqlProvider;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
@Mapper
public interface SqlMapper {
    //查询
    List<JSONObject> select(@Param("sql") String sql);

    //更新
    int update(@Param("sql") String sql);



}
