package com.yypt.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 *
 * @author ZHK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Integer dictId;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 备注
     */
    private String dictMemo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    private Integer dictStatus;


    /**
     * 类型
     * 1 为系统字典  2为业务字典
     */
    private Integer dictType;


}
