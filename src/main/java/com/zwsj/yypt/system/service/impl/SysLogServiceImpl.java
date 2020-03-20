package com.zwsj.yypt.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwsj.yypt.common.annotation.Log;
import com.zwsj.yypt.common.domain.QueryRequest;
import com.zwsj.yypt.common.properties.YyptConstant;
import com.zwsj.yypt.common.utils.AddressUtil;
import com.zwsj.yypt.common.utils.SortUtil;
import com.zwsj.yypt.system.dao.SysLogMapper;
import com.zwsj.yypt.system.domain.SysLog;
import com.zwsj.yypt.system.domain.SysLoginLog;
import com.zwsj.yypt.system.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;


/**
 * @创建人 zhk
 * @创建时间 2019-08-09
 * @描述
 */
@Service
@Slf4j
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper,SysLog> implements SysLogService {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void saveLog(ProceedingJoinPoint joinPoint, SysLog log) throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            // 注解上的描述
            log.setOperation(logAnnotation.value());
        }
        // 请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        log.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            params = handleParams(params, args, Arrays.asList(paramNames));
            log.setParams(params.toString());
        }
        log.setCreateDate(new Date());
        log.setLocation(AddressUtil.getCityInfo(log.getIp()));
        // 保存系统日志
        save(log);
    }

    @Override
    public IPage<SysLog> list(QueryRequest<SysLog> request) {
        try {
            Page<SysLog> page = new Page<>();
            SortUtil.handlePageSort(request, page, "logId",YyptConstant.ORDER_DESC,true);
            return this.baseMapper.list(page,request.getQuerylist());
        } catch (Exception e) {
            log.error("查询登录历史异常", e);
            return null;
        }
    }


    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) throws JsonProcessingException {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Map) {
                Set set = ((Map) args[i]).keySet();
                List<Object> list = new ArrayList<>();
                List<Object> paramList = new ArrayList<>();
                for (Object key : set) {
                    list.add(((Map) args[i]).get(key));
                    paramList.add(key);
                }
                return handleParams(params, list.toArray(), paramList);
            } else {
                if (args[i] instanceof Serializable) {
                    Class<?> aClass = args[i].getClass();
                    try {
                        aClass.getDeclaredMethod("toString", new Class[]{null});
                        // 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走 Object的 toString方法
                        params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                    } catch (NoSuchMethodException e) {
                        params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
                    }
                } else if (args[i] instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) args[i];
                    params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
                } else {
                    params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
                }
            }
        }
        return params;
    }
}
