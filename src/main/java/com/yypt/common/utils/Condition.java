package com.yypt.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.CaseFormat;
import com.yypt.common.annotation.Dict;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


/**
 * 构建查询条件
 */
public class Condition {

    public static QueryWrapper getQueryWrapper(Object queryObj,boolean isOr){

        Field[] fields = queryObj.getClass().getDeclaredFields();
        QueryWrapper queryWrapper = new QueryWrapper();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            String name = field.getName();
            name = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,name);
            Object fieldObj = ReflectionUtils.getField(field, queryObj);


            if(field.isAnnotationPresent(Dict.class)){
                if(fieldObj != null && StringUtils.isNotBlank(String.valueOf(fieldObj))){
                    queryWrapper.eq(name,fieldObj);
                }
                continue;
            }

            if(fieldObj instanceof  String){
                if (StringUtils.isNotBlank(String.valueOf(fieldObj))) {
                    queryWrapper.like(name,fieldObj);
                }
            }else if(fieldObj instanceof Long){
                if (fieldObj != null) {
                    queryWrapper.eq(name,fieldObj);
                }
            }else if(fieldObj instanceof  Integer){
                if (fieldObj != null) {
                    queryWrapper.eq(name,fieldObj);
                }
            }

            if(isOr){
                queryWrapper.or();
            }
        }

        return queryWrapper;
    }
}
