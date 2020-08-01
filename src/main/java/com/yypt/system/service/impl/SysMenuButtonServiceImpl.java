package com.yypt.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yypt.system.dao.SysMenuButtonMapper;
import com.yypt.system.dao.SysRoleButtonMapper;
import com.yypt.system.domain.SysMenuButton;
import com.yypt.system.domain.SysRoleButton;
import com.yypt.system.service.SysMenuButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-07-08
 * @描述
 */
@Service
public class SysMenuButtonServiceImpl extends ServiceImpl<SysMenuButtonMapper,SysMenuButton> implements SysMenuButtonService {

    @Autowired
    SysMenuButtonMapper sysMenuButtonMapper;


    @Autowired
    SysRoleButtonMapper sysRoleButtonMapper;



    @Override
    public List<SysMenuButton> getMenuButtonByRoleId(Long roleId) throws Exception{
        List<SysMenuButton> sysMenuButtons = sysMenuButtonMapper.getButtonByRoleId(roleId);
        return sysMenuButtons;
    }

    @Override
    @Transactional
    public void saveRoleButtons(Long roleId, Long menuId, JSONArray array) throws Exception {
        LambdaQueryWrapper<SysRoleButton> condition = new LambdaQueryWrapper<>();
        condition.eq(SysRoleButton::getMenuId,menuId);
        condition.eq(SysRoleButton::getRoleId,roleId);
        sysRoleButtonMapper.delete(condition);

        //插入角色button
        for(Object obj:array){
            String val = String.valueOf((Integer)obj);
            Long buttonId = Long.parseLong(val);
            SysRoleButton sysRoleButton = new SysRoleButton();
            sysRoleButton.setButtonId(buttonId);
            sysRoleButton.setRoleId(roleId);
            sysRoleButton.setCreateDate(new Date());
            sysRoleButton.setMenuId(menuId);
            sysRoleButtonMapper.insert(sysRoleButton);
        }
    }


    @Override
    @Transactional
    public void delete(Long buttonId) throws Exception {
        //删除对应的中间表
        LambdaQueryWrapper<SysRoleButton> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleButton::getButtonId,buttonId);
        sysRoleButtonMapper.delete(queryWrapper);

        //删除按钮
        this.baseMapper.deleteById(buttonId);

    }
}
