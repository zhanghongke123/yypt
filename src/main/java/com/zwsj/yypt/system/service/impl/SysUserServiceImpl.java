package com.zwsj.yypt.system.service.impl;

import com.zwsj.yypt.common.domain.QueryRequest;
import com.zwsj.yypt.common.properties.YyptConstant;
import com.zwsj.yypt.common.service.impl.BaseService;
import com.zwsj.yypt.common.utils.MD5Utils;
import com.zwsj.yypt.system.dao.SysRoleMapper;
import com.zwsj.yypt.system.dao.SysRoleUserMapper;
import com.zwsj.yypt.system.dao.SysUserMapper;
import com.zwsj.yypt.system.domain.SysRole;
import com.zwsj.yypt.system.domain.SysRoleUser;
import com.zwsj.yypt.system.domain.SysUser;
import com.zwsj.yypt.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
@Service
@Slf4j
public class SysUserServiceImpl extends BaseService<SysUser> implements SysUserService {
    @Autowired
    private  SysUserMapper sysUserMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysRoleUserMapper sysRoleUserMapper;

    @Override
    public SysUser findByName(String userName) {
        Example example = new Example(SysUser.class);
        example.createCriteria().andCondition("user_name = ",userName);
        List<SysUser> list =  this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }



    @Override
    public List<SysRole> getUserRoles(Long userId) {
        return sysRoleMapper.getUserRoles(userId);
    }

    @Override
    public List<SysUser> list(SysUser sysUser, QueryRequest request) {
        try {
            if (request.getSortField() != null) {
                sysUser.setSortField(request.getSortField());
                if (StringUtils.equals(YyptConstant.ORDER_ASC, request.getSortOrder()))
                    sysUser.setSortOrder("asc");
                else if (StringUtils.equals(YyptConstant.ORDER_DESC, request.getSortOrder()))
                    sysUser.setSortOrder("desc");
            }
            return sysUserMapper.list(sysUser);
        } catch (Exception e) {
            log.error("查询用户异常", e);
            return new ArrayList<>();
        }
    }

    @Override
    public SysUser addUser(SysUser sysUser) {
        sysUser.setCreateDate(new Date());
        sysUser.setModifyDate(new Date());
        //默认密码123456
        sysUser.setUserPassword(MD5Utils.encrypt(sysUser.getUserName(),"123456"));
        this.save(sysUser);
        this.setUserRoles(sysUser);

        return sysUser;
    }



    private void setUserRoles(SysUser sysUser){
        String[] roles = sysUser.getRoleIds().split(",");
        Arrays.stream(roles).forEach(roleId -> {
            SysRoleUser ur = new SysRoleUser();
            ur.setUserId(sysUser.getUserId());
            ur.setRoleId(Long.valueOf(roleId));
            ur.setCreateDate(new Date());
            this.sysRoleUserMapper.insert(ur);
        });

    }


}
