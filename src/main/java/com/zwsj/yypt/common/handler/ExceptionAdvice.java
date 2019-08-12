package com.zwsj.yypt.common.handler;


import com.zwsj.yypt.common.domain.YyptResponse;
import com.zwsj.yypt.common.enums.ResultEnum;
import com.zwsj.yypt.common.exception.RedisConnectException;
import com.zwsj.yypt.common.exception.YyptException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 zhk
 * @创建时间 2019-03-07
 * @描述
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {



    /**
     * 单独捕捉Shiro(UnauthorizedException)异常
     * 该异常为访问有权限管控的请求而该用户没有所需权限所抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public YyptResponse handle401(UnauthorizedException e) {
        return new YyptResponse().failure(ResultEnum.PERMISSIONS_ERROR,
                "没有接口的访问权限(" + e.getMessage() + ")");
    }

    /**
     * 单独捕捉Shiro(UnauthenticatedException)异常
     * 该异常为以游客身份访问有权限管控的请求无法对匿名主体进行授权，而授权失败所抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public YyptResponse handle401(UnauthenticatedException e) {
        return new YyptResponse().failure(ResultEnum.AUTHENTICATED_ERROR,
                "无权访问:请先登录."+e.getMessage());
    }


    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public YyptResponse handle401(ShiroException e) {
        return YyptResponse.failure(ResultEnum.AUTHENTICATED_ERROR,
                "授权错误:"+e.getMessage());
    }


    @ExceptionHandler(AuthenticationException.class)
    public YyptResponse handleMyUnauthorizedException(AuthenticationException e){
        return YyptResponse.failure(ResultEnum.AUTHENTICATED_ERROR,
                "认证失败:"+e.getMessage());
    }



    @ExceptionHandler(RedisConnectException.class)
    public YyptResponse handRedisConnectException(RedisConnectException e){
        return YyptResponse.failure(ResultEnum.REDIS_CONNECT_ERROR);

    }




    /**
     * 捕捉校验异常(BindException)
     * @return
     */
    @ExceptionHandler(BindException.class)
    public YyptResponse validException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, Object> result = this.getValidError(fieldErrors);
        return new YyptResponse().failure(ResultEnum.PARAMETER_ERROR,
                result.get("errorMsg").toString());
    }




    /**
     * 捕捉校验异常(MethodArgumentNotValidException)
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public YyptResponse validException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, Object> result = this.getValidError(fieldErrors);
        return new YyptResponse().failure(ResultEnum.PARAMETER_ERROR,
                result.get("errorMsg").toString());
    }



    /**
     * 捕捉404异常
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public YyptResponse handle(NoHandlerFoundException e) {
        return new YyptResponse().failure(ResultEnum.NOTFOUND_ERROR,
                e.getMessage());
    }



    /**
     * 捕捉其他所有异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public YyptResponse globalException(HttpServletRequest request, Throwable ex) {
        return new YyptResponse().failure(ResultEnum.OTHER_ERROR,
                 ex.getMessage());
    }


    @ExceptionHandler(value = YyptException.class)
    public YyptResponse handleParamsInvalidException(YyptException e) {
        log.error("系统错误：{}", e.getMessage());
        return new YyptResponse().failure(ResultEnum.OTHER_ERROR,
                e.getMessage());
    }




    /**
     * 获取校验错误信息
     * @param fieldErrors
     * @return
     */
    private Map<String, Object> getValidError(List<FieldError> fieldErrors) {
        Map<String, Object> result = new HashMap<String, Object>(16);
        List<String> errorList = new ArrayList<String>();
        StringBuffer errorMsg = new StringBuffer("校验异常:");
        for (FieldError error : fieldErrors) {
            errorList.add(error.getField() + "-" + error.getDefaultMessage());
            errorMsg.append(error.getField() + "-" + error.getDefaultMessage() + ".");
        }
        result.put("errorList", errorList);
        result.put("errorMsg", errorMsg);
        return result;
    }
}
