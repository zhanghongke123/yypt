package com.zwsj.yypt.common.utils;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwsj.yypt.common.authentication.jwt.JWTFilter;
import com.zwsj.yypt.common.exception.YyptException;
import com.zwsj.yypt.common.properties.YyptConstant;
import com.zwsj.yypt.common.service.RedisService;
import com.zwsj.yypt.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 zhk
 * @创建时间 2019-03-01
 * @描述
 */
@Slf4j
public class YyptUtils {

    @Autowired
    RedisService redisService;



    /**
     * token 加密
     *
     * @param token token
     * @return 加密后的 token
     */
    public static String encryptToken(String token) {
        try {
            EncryptUtil encryptUtil = new EncryptUtil(YyptConstant.EncryStr);
            return encryptUtil.encrypt(token);
        } catch (Exception e) {
            log.info("token加密失败：", e);
            return null;
        }
    }


    /**
     * token 解密
     *
     * @param encryptToken 加密后的 token
     * @return 解密后的 token
     */
    public static String decryptToken(String encryptToken) {
        try {
            EncryptUtil encryptUtil = new EncryptUtil(YyptConstant.EncryStr);
            return encryptUtil.decrypt(encryptToken);
        } catch (Exception e) {
            log.info("token解密失败：", e);
            return null;
        }
    }


    /**
     * 通过token获取对用的用户信息
     * @param request
     * @return
     */
    public static SysUser getUserByToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader(JWTFilter.TOKEN);
        if(StringUtils.isBlank(token)){
            throw new TokenExpiredException("Token已过期");
        }
        RedisService redisService = SpringContextUtil.getBean(RedisService.class);
        String userstr = redisService.get(YyptConstant.USER_TOKEN_CACHE_PREFIX + token);
        if(StringUtils.isBlank(userstr)){
            throw  new YyptException("redis获取用户信息失败");
        }
        ObjectMapper mapper = SpringContextUtil.getBean(ObjectMapper.class);
        return mapper.readValue(userstr,SysUser.class);
    }







}
