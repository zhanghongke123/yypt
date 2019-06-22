package com.zwsj.yypt.common.domain.route;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
@Data
public class RouteData {
    private String path;
    private String component;
    private String name;
    private String redirect;
    private Meta meta;
    private Long parentid;
    private Long id;
    private List<RouteData> children;

    public void initChildren(){
        this.children = new ArrayList<>();
    }
}
