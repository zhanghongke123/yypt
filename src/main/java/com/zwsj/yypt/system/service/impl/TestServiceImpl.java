package com.zwsj.yypt.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zwsj.yypt.system.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @创建人 zhk
 * @创建时间 2019-08-14
 * @描述
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @DS("#db")
    public void testDb(String db, String sql) {
        List<Map<String,Object>> aa = jdbcTemplate.queryForList(sql);
        System.out.println("**************************"+aa.size());

    }
}
