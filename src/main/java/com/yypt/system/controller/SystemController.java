package com.yypt.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.yypt.common.domain.YyptResponse;
import com.yypt.common.service.CacheService;
import com.yypt.common.utils.idgen.IdGenerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import com.yypt.system.dao.SqlMapper;

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

    @Autowired
    SqlMapper sqlMapper;
    





   @RequestMapping("sql")
   public  YyptResponse sql(@RequestBody JSONObject req){
       return YyptResponse.success(sqlMapper.select(req.getString("sql")));
   }

    /**
      * 生成验证码
      */
    @RequestMapping("getVerify")
    public YyptResponse getVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 设置响应的类型格式为图片格式
            String uuid = IdGenerate.uuid();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
            // 设置字体
            specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置

            // 设置类型，纯数字、纯字母、字母数字混合
            specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
            specCaptcha.out(outputStream);
            String code = specCaptcha.text();
            cacheService.saveCode(uuid,code);
            Map<String, Object> result = new HashMap<String, Object>();
            byte[] bytes = outputStream.toByteArray();
            result.put("image", "data:image/gif;base64," + Base64.getEncoder().encodeToString(bytes));
            result.put("uuid", uuid);
            return YyptResponse.success(result);
        } catch (Exception e) {
            log.error("获取验证码失败>>>> ", e);
            throw  e;
        }
    }


}
