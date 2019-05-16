package com.zwsj.yypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.zwkj.yypt.*.dao")
public class YyptApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyptApplication.class, args);
    }

}
