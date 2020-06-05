package com.yypt.system.controller;

import com.yypt.common.domain.YyptResponse;
import com.yypt.common.service.CacheService;
import com.yypt.common.utils.idgen.IdGenerate;
import com.yypt.common.utils.image.CaptchaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 zhk
 * @创建时间 2020-05-20
 * @描述
 */
@RequestMapping("system")
@Slf4j
@RestController
public class SystemController {

    @Autowired
    CacheService cacheService;




    /**
      * 生成验证码
      */
    @RequestMapping("getVerify")
    public YyptResponse getVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 设置响应的类型格式为图片格式
            String uuid = IdGenerate.uuid();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String code = CaptchaUtils.generateCaptcha(outputStream);
            cacheService.saveCode(uuid,code);
            Map<String, Object> result = new HashMap<String, Object>();
            byte[] bytes = outputStream.toByteArray();
            result.put("image", "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes));
            result.put("uuid", uuid);
            return YyptResponse.success(result);
        } catch (Exception e) {
            log.error("获取验证码失败>>>> ", e);
            throw  e;
        }
    }


}
