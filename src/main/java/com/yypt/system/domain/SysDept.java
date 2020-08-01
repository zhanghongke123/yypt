package com.yypt.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-07-12
 * @描述
 */
@TableName("sys_dept")
@Data
public class SysDept {

    /**
     *
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;


    /**
     * 部门名称
     */
    @TableField("dept_name")
    private String deptName;

    /**
     * 排序号
     */
    @TableField("order_code")
    private Long orderCode;


    /**
     * 父级ID
     */
    @TableField("parentid")
    private Long parentid;

    /**
     * 备注
     */
    @TableField("memo")
    private String memo;

    /**
     * 部门编码
     */
    @TableField("code")
    private String code;

    /**
     * 创建日期
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 修改日期
     */
    @TableField("modify_date")
    private Date modifyDate;



    private transient List<SysDept> children;


    public void initChildren(){
        this.children = new ArrayList<SysDept>();
    }
}
