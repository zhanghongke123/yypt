package com.zwsj.yypt.system.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "web_login_log")
@ToString
@Data
public class LoginLog {

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Long id;

    /**
     * 用户 ID
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登录时间
     */
    @Column(name = "LOGIN_TIME")
    private Date loginTime;

    /**
     * 登录地点
     */
    @Column(name = "LOCATION")
    private String location;

    @Column(name = "IP")
    private String ip;
}