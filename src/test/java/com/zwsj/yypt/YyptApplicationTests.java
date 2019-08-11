package com.zwsj.yypt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwsj.yypt.common.utils.MD5Utils;
import com.zwsj.yypt.system.dao.*;
import com.zwsj.yypt.system.domain.SysRole;
import com.zwsj.yypt.system.domain.SysRoleUser;
import com.zwsj.yypt.system.domain.SysUser;
import com.zwsj.yypt.system.service.SysLoginLogService;
import com.zwsj.yypt.system.service.SysMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YyptApplicationTests {
    @Autowired
    SqlMapper sqlMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SysMenuService sysMenuService;


    @Autowired
    SysLoginLogService sysLoginLogService;


    @Autowired
    SysMenuMapper sysMenuMapper;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testMapper() {
        String sql = "update user a set a.mobile='12' where a.user_id=1";
      int aa = sqlMapper.update(sql);
      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+aa);
    }


    @Test
    public  void  testId(){
        SysUser sysUser = new SysUser();
        sysUser.setUserName("admin123456");
        sysUser.setUserPassword(MD5Utils.encrypt("admin123456","123456"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>"+sysUserMapper.insert(sysUser));;
        System.out.println(">>>>>>>>>>>>>>>>>>>>"+sysUser.getUserId());
    }


    @Test
    public void insert(){

        SysUser sysUser = new SysUser();
        sysUser.setUserName("zhk");
        sysUser.setUserPassword(MD5Utils.encrypt("zhk","123456"));

        sysUserMapper.insert(sysUser);

        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试员");
        sysRole.setRoleCode("gly");
        sysRole.setCreateDate(new Date());
        sysRole.setModifyDate(new Date());
        sysRoleMapper.insert(sysRole);


        SysRoleUser sysRoleUser = new SysRoleUser();
        sysRoleUser.setUserId(sysUser.getUserId());
        sysRoleUser.setRoleId(sysRole.getRoleId());
        sysRoleUser.setCreateDate(new Date());
        sysRoleUserMapper.insert(sysRoleUser);

    }


    @Test
    public void findRole(){
//        List<SysRole> lit = sysRoleMapper.findUserRole(18L);
//        for (SysRole role:lit){
//            System.out.println(role.toString());
//        }

        sysLoginLogService.saveLoginLog("123");


    }


    @Test
    public void genRoute(){
//        List<RouteData> aa = TreeUtil.buildVueRouter(sysMenuMapper.);
//        try {
//            System.out.println("........"+objectMapper.writeValueAsString(aa));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

    }


}
