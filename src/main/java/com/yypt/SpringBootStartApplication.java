package com.yypt;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @创建人 zhk
 * @创建时间 2019-05-05
 * @描述
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意的Application是启动类，就是main方法所属的类
        return builder.sources(YyptApplication.class);
    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
//        c.setIgnoreUnresolvablePlaceholders(true);
//        return c;
//    }



}
