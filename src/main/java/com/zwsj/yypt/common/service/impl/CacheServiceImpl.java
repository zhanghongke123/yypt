package com.zwsj.yypt.common.service.impl;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwsj.yypt.common.properties.YyptProperties;
import com.zwsj.yypt.common.properties.YyptConstant;
import com.zwsj.yypt.common.service.CacheService;
import com.zwsj.yypt.common.service.RedisService;
import com.zwsj.yypt.system.domain.SysRole;
import com.zwsj.yypt.system.domain.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    @Autowired
    RedisService redisService;

    @Autowired
    YyptProperties yyptProperties;



    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void testConnect() throws Exception {
        redisService.exists("test");
    }

    @Override
    public void saveToken(String ip, String token) throws Exception {
        this.redisService.set(YyptConstant.TOKEN_CACHE_PREFIX + token + "." + ip,
                token, yyptProperties.getShiro().getJwtTimeOut() * 1000);

    }

    @Override
    public boolean tokenIsExist(String ip, String token) throws Exception {
        String enctoken = redisService.get(YyptConstant.TOKEN_CACHE_PREFIX + token + "." + ip);
        if(StringUtils.isNotBlank(enctoken)){
           return true;
        }
        return false;
    }

    @Override
    public List<SysRole> getRoles(String token) throws Exception {
        return this.getUserInfo(token).getRoleList();
    }

    @Override
    public void saveTokenUser(String token, SysUser sysUser) throws Exception {
        //缓存用户
        this.redisService.set(YyptConstant.USER_TOKEN_CACHE_PREFIX + token,
                objectMapper.writeValueAsString(sysUser),
                yyptProperties.getShiro().getJwtTimeOut() * 1000);

    }

    @Override
    public void savePermissions(String token, Set<String> permissions) throws Exception {

        //权限信息保存至redis
        redisService.set(YyptConstant.USER_PERMISSION_CACHE_PREFIX+token,
                objectMapper.writeValueAsString(permissions),
                yyptProperties.getShiro().getJwtTimeOut() * 1000);

    }

    @Override
    public Set<String> getPermissions(String token) throws Exception {
        //获取权限
        String permissionstr = redisService.get(YyptConstant.USER_PERMISSION_CACHE_PREFIX+token);
        JavaType type1 = objectMapper.getTypeFactory().constructParametricType(Set.class, String.class);
        Set<String> permissions =  objectMapper.readValue(permissionstr,type1);
        return permissions;
    }

    @Override
    public void clearToken(String token,String ip) throws Exception {
        redisService.del(YyptConstant.TOKEN_CACHE_PREFIX + token + "." + ip);
    }

    @Override
    public void clearTokenUser(String token) throws Exception {
        redisService.del(YyptConstant.USER_TOKEN_CACHE_PREFIX + token);

    }

    @Override
    public void clearPermissions(String token) throws Exception {
        redisService.del(YyptConstant.USER_PERMISSION_CACHE_PREFIX+token);
    }


    public SysUser getUserInfo(String token) throws Exception {

        String sysuserStr = this.redisService.get(YyptConstant.USER_TOKEN_CACHE_PREFIX + token);
        if(StringUtils.isBlank(sysuserStr)){
            throw  new Exception("从缓存读取用户信息失败");
        }
        SysUser sysUser = objectMapper.readValue(sysuserStr,SysUser.class);
        if(sysUser == null){
            throw  new Exception("从缓存读取用户信息失败");
        }

        return sysUser;
    }





}
