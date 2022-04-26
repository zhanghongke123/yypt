package com.yypt.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author ZHK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDictDtl implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "dict_dtl_id", type = IdType.AUTO)
    private Integer dictDtlId;

    private Integer dictId;

    private String label;

    private String value;

    private String memo;

    /**
     * 排序
     */
    private Integer orderNo;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
