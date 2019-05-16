package com.zwsj.yypt.common.domain.route;

import lombok.Data;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
@Data
public class Meta {
    public Meta(String title,String icon){
        this.title = title;
        this.icon = icon;
    }
    private String title;
    private String icon;
}
