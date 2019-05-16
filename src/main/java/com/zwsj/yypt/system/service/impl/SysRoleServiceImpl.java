package com.zwsj.yypt.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zwsj.yypt.common.service.impl.BaseService;
import com.zwsj.yypt.system.dao.SysRoleMapper;
import com.zwsj.yypt.system.domain.SysRole;
import com.zwsj.yypt.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
@Service
public class SysRoleServiceImpl extends BaseService<SysRole> implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;


    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        return sysRoleMapper.getUserRoles(userId);
    }


}
