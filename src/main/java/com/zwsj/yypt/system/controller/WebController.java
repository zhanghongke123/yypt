package com.zwsj.yypt.system.controller;

import com.zwsj.yypt.common.exception.MyUnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 zhk
 * @创建时间 2019-03-11
 * @描述
 */
@RestController
public class WebController {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @GetMapping("/unauthorized")
    public void dealUnauthorized(HttpServletRequest request){
        throw  new MyUnauthorizedException(request.getParameter("errormsg"));
    }
}
