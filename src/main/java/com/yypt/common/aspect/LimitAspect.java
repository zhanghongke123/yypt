package com.yypt.common.aspect;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.ImmutableList;
import com.yypt.common.annotation.Limit;
import com.yypt.common.authentication.jwt.JWTFilter;
import com.yypt.common.authentication.jwt.JWTUtil;
import com.yypt.common.enums.LimitType;
import com.yypt.common.exception.LimitAccessException;
import com.yypt.common.utils.IPUtil;
import com.yypt.common.utils.YyptUtils;
import com.yypt.system.dao.SysUserMapper;
import com.yypt.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;


/**
 * 接口限流
 */
@Slf4j
@Aspect
@Component
public class LimitAspect {

    @Autowired
    SysUserMapper sysUserMapper;

    private final RedisTemplate<String, Serializable> limitRedisTemplate;

    @Autowired
    public LimitAspect(RedisTemplate<String, Serializable> limitRedisTemplate) {
        this.limitRedisTemplate = limitRedisTemplate;
    }

    @Pointcut("@annotation(com.yypt.common.annotation.Limit)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Limit limitAnnotation = method.getAnnotation(Limit.class);
        LimitType limitType = limitAnnotation.limitType();
        String name = limitAnnotation.name();
        String token = request.getHeader(JWTFilter.TOKEN);
        String key = "";
        String ip = IPUtil.getIpAddr(request);
        int limitPeriod = limitAnnotation.period();
        int limitCount = limitAnnotation.count();
        switch (limitType) {
            case IP:
                key = ip;
                break;
            case CUSTOMER:
                key = limitAnnotation.key();
                break;
            case Token:
                if(StringUtils.isEmpty(token)){
                    log.error("描述为 [{}] 的接口 验证方式为Token Token为空取方法的名称",name);
                    key = StringUtils.upperCase(method.getName());
                }
                break;
            default:
                key = StringUtils.upperCase(method.getName());
        }
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnnotation.prefix() + "_", key, ip));
        String luaScript = buildLuaScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
        Number count = limitRedisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
        log.info("IP:{} 第 {} 次访问key为 {}，描述为 [{}] 的接口", ip,  count, keys, name);
        if (count != null && count.intValue() <= limitCount) {
            return point.proceed();
        } else {
            if(limitType == LimitType.Token){
               //如果是通过token验证的话,超过频率的话，冻结用户
                if(StringUtils.isNotEmpty(token)){
                    String userName = JWTUtil.getUsername(YyptUtils.decryptToken(token));
                    SysUser sysUser = new SysUser();
                    sysUser.setLockDate(new Date());
                    sysUser.setStatus(2L);
                    String memo = " 接口 %s 方法 %s 超过访问频率 将用户进行冻结" ;
                    sysUser.setMemo(String.format(memo,name,method.getName()));
                    LambdaQueryWrapper<SysUser> updateWhere = new LambdaQueryWrapper<>();
                    updateWhere.eq(SysUser::getUserName,userName);
                    sysUserMapper.update(sysUser,updateWhere);
                }

            }
            throw new LimitAccessException("接口访问超出频率限制");
        }
    }

    /**
     * 限流脚本
     * 调用的时候不超过阈值，则直接返回并执行计算器自加。
     *
     * @return lua脚本
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }

}
