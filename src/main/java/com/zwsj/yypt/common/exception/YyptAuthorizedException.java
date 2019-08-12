package com.zwsj.yypt.common.exception;

import com.zwsj.yypt.common.enums.ResultEnum;
import org.apache.shiro.authc.AuthenticationException;


/**
 * @创建人 zhk
 * @创建时间 2019-08-12
 * @描述
 */
public class YyptAuthorizedException extends AuthenticationException {
    private  ResultEnum resultEnum;
    private int code;
    private String msg;
    public YyptAuthorizedException(int code, String msg){
         super(msg);
         this.code = code;
         this.msg = msg;
    }

    public YyptAuthorizedException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.resultEnum = resultEnum;
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}
