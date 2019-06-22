package com.zwsj.yypt.common.utils;


import com.zwsj.yypt.common.domain.route.Meta;
import com.zwsj.yypt.common.domain.route.RouteData;
import com.zwsj.yypt.system.domain.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {


    protected TreeUtil() {
    }

    private final static Long TOP_NODE_ID = 0L;




    public static List<RouteData> buildVueRouter(List<SysMenu> routes) {
        if (routes == null) {
            return null;
        }

        List<RouteData> routeDataList = new ArrayList<RouteData>();
        routes.forEach(route ->{
            RouteData routeData = new RouteData();
            routeData.setId(route.getMenuId());
            routeData.setComponent(route.getComponent());
            routeData.setMeta(new Meta(route.getName(),route.getIcon()));
            routeData.setName(route.getName());
            routeData.setParentid(route.getParentid());
            routeData.setPath(route.getPath());
            routeData.setRedirect("");
            routeDataList.add(routeData);
        });

        List<RouteData> topRoutes = new ArrayList<>();
        routeDataList.forEach(route -> {

            Long parentId = route.getParentid();
            if (parentId == null || TOP_NODE_ID == parentId) {
                topRoutes.add(route);
                return;
            }

            for (RouteData parent : routeDataList) {
                Long id = parent.getId();
                if (id != null && id == parentId) {
                    if (parent.getChildren() == null){
                        parent.initChildren();
                    }
                    parent.getChildren().add(route);
                    return;
                }
            }
        });

        return topRoutes;
    }
}