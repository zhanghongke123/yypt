package com.zwsj.yypt.common.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface YyptMapper<T> extends Mapper<T>,MySqlMapper<T> {

}