package com.zwsj.yypt.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhk
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "login_id", type = IdType.AUTO)
    private Integer loginId;

    private String userName;

    private LocalDateTime loginTime;

    private String location;

    private String ip;


}
