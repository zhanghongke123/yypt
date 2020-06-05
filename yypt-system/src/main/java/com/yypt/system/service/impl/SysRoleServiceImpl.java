package com.yypt.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.properties.YyptConstant;
import com.yypt.common.utils.SortUtil;
import com.yypt.system.dao.*;
import com.yypt.system.domain.*;
import com.yypt.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements SysRoleService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    SysRoleButtonMapper sysRoleButtonMapper;


    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        return sysRoleMapper.getUserRoles(userId);
    }

    @Override
    public List<SysRole> list(SysRole sysRole) {
        List<SysRole> data = sysRoleMapper.list(sysRole);
        return data;
    }

    @Override
    public List<SysRoleUser> getRoleUsers(SysRole sysRole) {
        SysRoleUser sysRoleUser = new SysRoleUser();
        sysRoleUser.setRoleId(sysRole.getRoleId());
        return sysRoleUserMapper.getRoleUsers(sysRoleUser);
    }

    @Override
    public SysRole updateOrAdd(SysRole sysRole) {
        //有ID的话就是修改，没有ID的话就是保存
        Long roleId = sysRole.getRoleId();
        if(roleId != null){
            sysRole.setModifyDate(new Date());
            this.baseMapper.updateById(sysRole);
        }else{
            sysRole.setModifyDate(new Date());
            sysRole.setCreateDate(new Date());
            this.baseMapper.insert(sysRole);
        }
        List<SysRole> sysRoles = this.baseMapper.list(sysRole);
        return sysRoles.size() > 0 ? sysRoles.get(0):null;
    }

    @Override
    @Transactional
    public void del(SysRole sysRole) throws Exception {
        //删除角色对应的人员
        LambdaQueryWrapper<SysRoleUser> roleusercond = new LambdaQueryWrapper<>();
        roleusercond.eq(SysRoleUser::getRoleId,sysRole.getRoleId());
        sysRoleUserMapper.delete(roleusercond);


        //删除角色对应的菜单
        LambdaQueryWrapper<SysRoleMenu> rolemenucond = new LambdaQueryWrapper<>();
        rolemenucond.eq(SysRoleMenu::getRoleId,sysRole.getRoleId());
        sysRoleMenuMapper.delete(rolemenucond);


        //删除角色对应的按钮
        LambdaQueryWrapper<SysRoleButton> rolebuttoncond = new LambdaQueryWrapper<>();
        rolebuttoncond.eq(SysRoleButton::getRoleId,sysRole.getRoleId());
        sysRoleButtonMapper.delete(rolebuttoncond);


        //删除角色
        this.baseMapper.deleteById(sysRole.getRoleId());


    }

    @Override
    @Transactional
    public void delRoleUser(SysRoleUser sysRoleUser) throws Exception {
        sysRoleUserMapper.deleteById(sysRoleUser.getRoleUserId());
    }

    @Override
    public IPage<SysUser> userRep(QueryRequest<SysUser> request) {
        try {
            Page<SysUser> page = new Page<>();
            SortUtil.handlePageSort(request, page, "userId",YyptConstant.ORDER_DESC,true);
            return sysUserMapper.userRep(page,request.getQuerylist());
        } catch (Exception e) {
            log.error("查询用户异常", e);
            return null;
        }
    }

    @Override
    @Transactional
    public void saverRoleUser(Long roleId, String userIds) {
        String[] userIdArry = userIds.split(",");
        for(String userId:userIdArry){
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setCreateDate(new Date());
            sysRoleUser.setUserId(Long.parseLong(userId));
            sysRoleUserMapper.insert(sysRoleUser);
        }


    }


}
