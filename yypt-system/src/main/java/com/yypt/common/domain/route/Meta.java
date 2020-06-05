package com.yypt.common.domain.route;

import lombok.Data;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
@Data
public class Meta {
    private String title;
    private String icon;
    private boolean noCache;
    private List<YyptButton> buttons;
}
