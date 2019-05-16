package com.zwsj.yypt.common.service;


import com.zwsj.yypt.system.domain.SysRole;
import com.zwsj.yypt.system.domain.SysUser;

import java.util.List;
import java.util.Set;

public interface CacheService {

    /**
     * 测试 Redis是否连接成功
     */
    void testConnect() throws Exception;

    /**
     *缓存token
     * @param ip
     * @param token
     * @throws Exception
     */
    void saveToken(String ip, String token) throws Exception;

    /**
     * 检查token是否存在
     * @param ip
     * @param token
     * @return
     * @throws Exception
     */
    boolean tokenIsExist(String ip, String token) throws  Exception;

    /**
     * 从缓存获取角色列表
     * @param token
     * @return
     * @throws Exception
     */
    List<SysRole> getRoles(String token) throws  Exception;

    /**
     *缓存用户信息
     * @param token
     * @param object
     * @throws Exception
     */
    void saveTokenUser(String token, SysUser sysUser) throws Exception;


    /**
     * 保存权限信息
     * @param token
     * @param permissions
     * @throws Exception
     */
    void savePermissions(String token, Set<String> permissions) throws Exception;


    /**
     * 保存权限信息
     * @param token
     * @throws Exception
     */
    Set<String> getPermissions(String token) throws Exception;


    void clearToken(String token, String ip) throws Exception;


    void clearTokenUser(String token) throws  Exception;


    void clearPermissions(String token) throws  Exception;



}
