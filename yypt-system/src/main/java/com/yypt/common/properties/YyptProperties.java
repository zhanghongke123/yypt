package com.yypt.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "yypt")
public class YyptProperties {

    private ShiroProperties shiro = new ShiroProperties();

    private boolean openAopLog = true;


    private int codetimeout = 60;

    public ShiroProperties getShiro() {
        return shiro;
    }

    public void setShiro(ShiroProperties shiro) {
        this.shiro = shiro;
    }

    public boolean isOpenAopLog() {
        return openAopLog;
    }

    public void setOpenAopLog(boolean openAopLog) {
        this.openAopLog = openAopLog;
    }

    public int getCodetimeout() {
        return codetimeout;
    }

    public void setCodetimeout(int codetimeout) {
        this.codetimeout = codetimeout;
    }
}
