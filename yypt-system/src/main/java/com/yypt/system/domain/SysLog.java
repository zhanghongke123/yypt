package com.yypt.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_log")
public class SysLog implements Serializable {

    private static final long serialVersionUID = -8878596941954995444L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;


    @TableField("username")
    private String username;

    @TableField("operation")
    private String operation;

    @TableField("time")
    private Long time;

    @TableField("method")
    private String method;

    @TableField("params")
    private String params;


    @TableField("ip")
    private String ip;

    @TableField("location")
    private String location;

    @TableField("create_date")
    private Date createDate;

    private transient String createTimeFrom;
    private transient String createTimeTo;


}