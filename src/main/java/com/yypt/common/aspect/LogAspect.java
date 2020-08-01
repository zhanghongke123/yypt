package com.yypt.common.aspect;

import com.yypt.common.authentication.jwt.JWTFilter;
import com.yypt.common.authentication.jwt.JWTUtil;
import com.yypt.common.properties.YyptProperties;
import com.yypt.common.utils.HttpContextUtil;
import com.yypt.common.utils.IPUtil;
import com.yypt.common.utils.YyptUtils;
import com.yypt.system.domain.SysLog;
import com.yypt.system.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP 记录用户操作日志
 *
 * @author MrBird
 * @link https://mrbird.cc/Spring-Boot-AOP%20log.html
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private YyptProperties yyptProperties;

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.yypt.common.annotation.Log)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        // 执行方法
        result = point.proceed();
        // 获取 request
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        // 设置 IP 地址
        String ip = IPUtil.getIpAddr(request);
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        if (yyptProperties.isOpenAopLog()) {
            // 保存日志
            String token = request.getHeader(JWTFilter.TOKEN);
            String username = "";
            if (StringUtils.isNotBlank(token)) {
                username = JWTUtil.getUsername(YyptUtils.decryptToken(token));
            }

            SysLog log = new SysLog();
            log.setUsername(username);
            log.setIp(ip);
            log.setTime(time);
            sysLogService.saveLog(point, log);
        }
        return result;
    }
}
