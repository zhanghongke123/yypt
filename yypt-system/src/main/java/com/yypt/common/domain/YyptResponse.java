package com.yypt.common.domain;


import com.yypt.common.enums.ResultEnum;

import java.util.HashMap;

public class YyptResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;


    public static YyptResponse success(Object data){
        YyptResponse resp = new YyptResponse();
        resp.put("code", ResultEnum.SUCCESS.getCode());
        resp.put("data",data);
        resp.put("message","请求成功");
        return resp;
    }



    public static YyptResponse failure(ResultEnum resultEnum,String msg){
        YyptResponse resp = new YyptResponse();
        resp.put("code", resultEnum.getCode());
        resp.put("data",null);
        resp.put("message",msg);
        return resp;
    }



    public static YyptResponse failure(String msg){
        YyptResponse resp = new YyptResponse();
        resp.put("code", ResultEnum.OTHER_ERROR.getCode());
        resp.put("data",null);
        resp.put("message",msg);
        return resp;
    }


    public static YyptResponse failure(ResultEnum resultEnum){
        YyptResponse resp = new YyptResponse();
        resp.put("code", resultEnum.getCode());
        resp.put("data",null);
        resp.put("message",resultEnum.getMessage());
        return resp;
}


    @Override
    public YyptResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
