package com.yypt.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yypt.common.annotation.Limit;
import com.yypt.common.authentication.jwt.JWTFilter;
import com.yypt.common.authentication.jwt.JWTToken;
import com.yypt.common.authentication.jwt.JWTUtil;
import com.yypt.common.domain.YyptResponse;
import com.yypt.common.enums.ResultEnum;
import com.yypt.common.properties.YyptProperties;
import com.yypt.common.service.CacheService;
import com.yypt.common.utils.*;
import com.yypt.system.domain.*;
import com.yypt.system.service.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
@RestController
@Validated

public class LoginController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    YyptProperties yyptProperties;

    @Autowired
    SysLoginLogService sysLoginLogService;

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    CacheService cacheService;

    @Autowired
    SysDeptService sysDeptService;

    @Autowired
    SysMenuButtonService sysMenuButtonService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/login")
    @Limit(key = "login", period = 60, count = 20, name = "登录接口", prefix = "limit")
    public YyptResponse login(@RequestBody Map<String,String> params, HttpServletRequest request) throws Exception{
        String username = params.get("username");
        String uuid = params.get("uuid");
        String code = params.get("code");
        if(StringUtils.isBlank(username)){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"用户名不能为空");
        }

        String password = params.get("password");
        if(StringUtils.isBlank(password)){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"密码不能为空");
        }

        if(StringUtils.isBlank(uuid)){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"验证码已失效");
        }

        if(StringUtils.isBlank(code)){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"请输入验证码");
        }

        String dbcode = cacheService.getCode(uuid);
        if(StringUtils.isBlank(dbcode)){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"验证码已失效，请刷新验证码");
        }

        if(!dbcode.equals(code)){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"验证码错误");
        }

        password = MD5Utils.encrypt(username,password);

        SysUser sysUser = sysUserService.findByName(username);
        if(sysUser == null){
            return YyptResponse.failure(ResultEnum.USER_NOTEXIST);
        }


        //停用
        if(sysUser.getStatus() == 0){
            return YyptResponse.failure(ResultEnum.USER_STOP);
        }

        //停用
        if(sysUser.getStatus() == 2){
            return YyptResponse.failure(ResultEnum.USER_LOCK);
        }

        if(!StringUtils.equals(password, sysUser.getUserPassword())){
            return YyptResponse.failure(ResultEnum.PASSWORD_ERROR);
        }


        Long deptId = sysUser.getDeptId();
        if(deptId != null ){
            SysDept sysDept = sysDeptService.getById(sysUser.getDeptId());
            sysUser.setDeptName(sysDept.getDeptName());
        }

        //获取用户角色
        List<SysRole> roles = sysUserService.getUserRoles(sysUser.getUserId());
        if(roles.size() == 0){
            return YyptResponse.failure(ResultEnum.NO_ROLE);
        }


        sysUser.setRoleList(roles);
        sysUser.setId(RandomStringUtils.randomAlphanumeric(20));
        JWTToken jwtToken = this.getToken(sysUser,request);
        Map<String, Object> res = this.generateUserInfo(jwtToken,sysUser);

        //保存登录记录
        sysLoginLogService.saveLoginLog(username);
        return YyptResponse.success(res);
    }


    @GetMapping("/getUserInfo")
    public YyptResponse getUserRoles(HttpServletRequest request) throws Exception{
        SysUser sysUser = YyptUtils.getUserByRequest(request);
        List<SysRole> roleList =  sysUserService.getUserRoles(sysUser.getUserId());
        sysUser.setRoleList(roleList);
        Map<String,Object> data = new HashMap<>();
        data.put("user",sysUser);
        //缓存用户
        cacheService.saveTokenUser(request.getHeader(JWTFilter.TOKEN),sysUser);
        return YyptResponse.success(data);
    }

    @GetMapping("/login/getMenu/{roleId}")
    public YyptResponse getUserRoles(@PathVariable(name = "roleId") String roleId,
                                     HttpServletRequest request) throws Exception{

        //生成路由信息
        List<SysMenu> menuList = sysMenuService.getMenusByRoleId(Long.parseLong(roleId));

        List<SysMenuButton> buttonList = sysMenuButtonService.getMenuButtonByRoleId(Long.parseLong(roleId));

        Set<String> menupermissions = menuList.stream().map(SysMenu::getPermission).collect(Collectors.toSet());

        Set<String> buttonpermissions = buttonList.stream().map(SysMenuButton::getPermission).collect(Collectors.toSet());

        menupermissions.addAll(buttonpermissions);
        //权限信息保存至redis
        cacheService.savePermissions(request.getHeader(JWTFilter.TOKEN),menupermissions);

        return YyptResponse.success(TreeUtil.buildVueRouter(menuList,buttonList));
    }


    @GetMapping("/logout")
    public YyptResponse logout(HttpServletRequest request) throws Exception{
        String token = request.getHeader(JWTFilter.TOKEN);
        cacheService.clearToken(token,IPUtil.getIpAddr(request));
        cacheService.clearPermissions(token);
        cacheService.clearTokenUser(token);
        return YyptResponse.success("登出成功");

    }


    /**
     * 获取Token
     * @param user
     * @param request
     * @return
     * @throws Exception
     */
    private JWTToken getToken(SysUser user, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIpAddr(request);
        String token = YyptUtils.encryptToken(JWTUtil.sign(user.getUserName(), user.getUserPassword()));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(yyptProperties.getShiro().getJwtTimeOut());
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);
        //保存Token到Redis
        cacheService.saveToken(ip,token);

        //缓存用户
        cacheService.saveTokenUser(token,user);

        return jwtToken;
    }


    /**
     * 生成用户信息用于返回
     * @param token
     * @param user
     * @return
     * @throws Exception
     */
    private Map<String, Object> generateUserInfo(JWTToken token, SysUser user) throws Exception {
        String username = user.getUserName();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("token", token.getToken());
        userInfo.put("exipretime", token.getExipreAt());
        user.setUserPassword("******");
        userInfo.put("userinfo", user);
        return userInfo;
    }

}
