package com.zwsj.yypt.system.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
@Table(name = "role_user")
@Data
public class SysRoleUser {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "role_user_id")
    private Long roleUserId;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "create_date")
    private Date createDate;
}
