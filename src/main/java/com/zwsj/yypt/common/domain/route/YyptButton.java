package com.zwsj.yypt.common.domain.route;

import lombok.Data;

/**
 * @创建人 zhk
 * @创建时间 2019-06-25
 * @描述
 */
@Data
public class YyptButton {
    public String name;

    public String permission;

    public String icon;

    public String requestPath;


    public Long menuId;

    public  Long orderCode;
}
