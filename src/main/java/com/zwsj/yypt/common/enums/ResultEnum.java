package com.zwsj.yypt.common.enums;

import lombok.Getter;


/**
 *
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),

    USER_NOTEXIST(2,"用户不存在"),

    PASSWORD_ERROR(3, "密码错误"),

    ACCONUT_LOCKED(4, "账号锁定"),

    NO_ROLE(6,"没有角色信息，禁止登陆"),

    REDIS_CONNECT_ERROR(5, "Redis连接异常"),

    NOTFOUND_ERROR(404,"没有找到对应的资源"),

    PARAMETER_ERROR(1, "参数错误"),

    PERMISSIONS_ERROR(5000, "验证接口权限失败"),


    AUTHENTICATED_ERROR(5001,"授权错误"),

    TOKEN_ERROR(5002,"TOKEN验证失败"),



    OTHER_ERROR(99999,"其他错误")


    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
