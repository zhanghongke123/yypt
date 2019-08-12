package com.zwsj.yypt.common.authentication.shiro;


import com.zwsj.yypt.common.authentication.jwt.JWTToken;
import com.zwsj.yypt.common.authentication.jwt.JWTUtil;
import com.zwsj.yypt.common.enums.ResultEnum;
import com.zwsj.yypt.common.exception.YyptAuthorizedException;
import com.zwsj.yypt.common.service.CacheService;
import com.zwsj.yypt.common.utils.YyptUtils;
import com.zwsj.yypt.common.utils.HttpContextUtil;
import com.zwsj.yypt.common.utils.IPUtil;
import com.zwsj.yypt.system.domain.SysRole;
import com.zwsj.yypt.system.domain.SysUser;
import com.zwsj.yypt.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    CacheService cacheService;


    @Autowired
    SysUserService sysUserService;


    /**
     * 必须重写此方法，不然Shiro会报错
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**`
     * 授权模块，获取用户角色和权限
     *
     * @param token token
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        try {
            List<SysRole> roleList =  cacheService.getRoles(token.toString());
            Set<String> roleSet = roleList.stream().map(SysRole::getRoleName).collect(Collectors.toSet());
            simpleAuthorizationInfo.setRoles(roleSet);


            Set<String> permissionSet =  cacheService.getPermissions(token.toString());
            simpleAuthorizationInfo.setStringPermissions(permissionSet);


        }catch (Exception e){

        }

        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
        String token = (String) authenticationToken.getCredentials();

        // 从 redis里获取这个 token
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);

        String encryptToken = YyptUtils.encryptToken(token);
        String encryptTokenInRedis = null;
        // 如果找不到，说明已经失效
        boolean IsExist = false;
        try {
            IsExist =  cacheService.tokenIsExist(ip,encryptToken);
        }catch (Exception e){
           log.error("检查是否有Token失败:{}",e);
        }
        if (IsExist == false){
            throw  new YyptAuthorizedException(ResultEnum.TOKEN_TIMEOUT);
        }

        String userName= JWTUtil.getUsername(token);

        if (StringUtils.isBlank(userName)){
            throw new YyptAuthorizedException(ResultEnum.TOKEN_ERROR);
        }

        // 通过用户名查询用户信息
        SysUser user = sysUserService.findByName(userName);
        if (user == null){
            throw new YyptAuthorizedException(ResultEnum.USER_NOTEXIST);
        }


        if (!JWTUtil.verify(token, userName, user.getUserPassword())){
            throw new YyptAuthorizedException(ResultEnum.PARAMETER_ERROR);
        }

        if(user.getStatus() == 0){
           throw  new YyptAuthorizedException(ResultEnum.USER_STOP);
        }

        if(user.getStatus() == 2){
            throw  new YyptAuthorizedException(ResultEnum.USER_LOCK);
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
