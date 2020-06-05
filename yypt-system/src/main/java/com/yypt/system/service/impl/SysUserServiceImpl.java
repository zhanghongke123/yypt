package com.yypt.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.properties.YyptConstant;
import com.yypt.common.utils.MD5Utils;
import com.yypt.common.utils.SortUtil;
import com.yypt.system.dao.SysRoleMapper;
import com.yypt.system.dao.SysRoleUserMapper;
import com.yypt.system.dao.SysUserMapper;
import com.yypt.system.domain.SysRole;
import com.yypt.system.domain.SysUser;
import com.yypt.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {
    @Autowired
    private  SysUserMapper sysUserMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysRoleUserMapper sysRoleUserMapper;

    @Override
    public SysUser findByName(String userName) {
        LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<>();
        query.eq(SysUser::getUserName,userName);
        return this.baseMapper.selectOne(query);
    }


    @Override
    public List<SysRole> getUserRoles(Long userId) {
        return this.sysRoleMapper.getUserRoles(userId);
    }

    @Override
    @Transactional
    public SysUser updateOrAdd(SysUser sysUser) throws Exception{
        //有ID的话就是修改，没有ID的话就是保存
        Long userId = sysUser.getUserId();
        if(userId != null){
            //更新用户
            SysUser dbuser = this.baseMapper.selectById(userId);
            //更新用户名的话，密码重置  更新手机号的话要验证唯一
            if(!dbuser.getUserName().equals(sysUser.getUserName())){
                //用户名发生改变 判断时候存在该用户
                SysUser res = this.findByName(sysUser.getUserName());
                if(res != null && res.getUserId() != userId){
                    throw new Exception("已存在该用户【"+sysUser.getUserName()+"】,请更护用户名");
                }
                String password = MD5Utils.encrypt(sysUser.getUserName(),"123456");
            }

            String dbphone = dbuser.getMobile();
            String phone = sysUser.getMobile();
            if(dbphone == null){
                dbphone = "";
            }
            //判断手机号是否重复，防止后期发送验证码验证
            if(!dbphone.equals(phone)){
                if(StringUtils.isNotBlank(phone)){
                    SysUser res1 = this.getUserByPhone(phone);
                    if(res1 != null && res1.getUserId() != userId){
                        throw new Exception("该手机号已被注册 【"+phone+"】");
                    }
                }
            }



            sysUser.setModifyDate(new Date());
            this.baseMapper.updateById(sysUser);
        }else{
            //判断用户名是否重复
            SysUser res = this.findByName(sysUser.getUserName());
            if(res != null){
               throw new Exception("已存在该用户【"+sysUser.getUserName()+"】,请更护用户名");
            }

            String phone = sysUser.getMobile();
            //判断手机号是否重复，防止后期发送验证码验证
            if(StringUtils.isNotBlank(phone)){
                SysUser res1 = this.getUserByPhone(phone);
                if(res1 != null){
                    throw new Exception("该手机号已被注册 【"+phone+"】");
                }
            }

            String password = MD5Utils.encrypt("123456",sysUser.getUserName());

            sysUser.setModifyDate(new Date());
            sysUser.setCreateDate(new Date());
            this.baseMapper.insert(sysUser);
        }
        return null;
    }


    /**
     * 通过手机号获取用户
     * @param phone
     * @return
     */
    public SysUser getUserByPhone(String phone){
        LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<>();
        query.eq(SysUser::getMobile,phone);
        return this.baseMapper.selectOne(query);
    }

    @Override
    @Transactional
    public void del(SysUser sysUser) {
        this.baseMapper.deleteById(sysUser.getUserId());
    }


    @Override
    public IPage<SysUser> list(QueryRequest<SysUser> request) {
        try {
            Page<SysUser> page = new Page<>();
            SortUtil.handlePageSort(request, page, "userId",YyptConstant.ORDER_DESC,true);
            return this.sysUserMapper.list(page,request.getQuerylist());
        } catch (Exception e) {
            log.error("查询用户异常", e);
            return null;
        }
    }







}
