package com.zwsj.yypt.common.exception;

import org.apache.shiro.authz.UnauthorizedException;

/**
 * @创建人 zhk
 * @创建时间 2019-03-10
 * @描述
 */
public class MyUnauthorizedException extends UnauthorizedException {
    public  MyUnauthorizedException(String msg){
        super(msg);
    }
}
