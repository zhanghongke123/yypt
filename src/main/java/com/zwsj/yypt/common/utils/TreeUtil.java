package com.zwsj.yypt.common.utils;


import com.zwsj.yypt.common.domain.Tree;
import com.zwsj.yypt.common.domain.route.Meta;
import com.zwsj.yypt.common.domain.route.RouteData;
import com.zwsj.yypt.system.domain.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {


    protected TreeUtil() {
    }

    private final static String TOP_NODE_ID = "0";

    /**
     * 用于构建菜单或部门树
     *
     * @param nodes nodes
     * @param <T>   <T>
     * @return <T> Tree<T>
     */
    public static <T> Tree<T> build(List<Tree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        nodes.forEach(node -> {

            String pid = node.getParentId();
            if (pid == null || TOP_NODE_ID.equals(pid)) {
                topNodes.add(node);
                return;
            }

            for (Tree<T> n : nodes) {
                String id = n.getId();
                if (id != null && id.equals(pid)) {
                    if (n.getChildren() == null)
                        n.initChildren();
                    n.getChildren().add(node);
                    node.setHasParent(true);
                    n.setHasChildren(true);
                    n.setHasParent(true);
                    return;
                }
            }
            if (topNodes.isEmpty())
                topNodes.add(node);
        });

        Tree<T> root = new Tree<>();
        root.setId("0");
        root.setParentId("");
        root.setHasParent(false);
        root.setHasChildren(true);
        root.setChildren(topNodes);
        root.setText("root");
        return root;
    }



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

            String parentId = route.getParentid();
            if (parentId == null || TOP_NODE_ID.equals(parentId)) {
                topRoutes.add(route);
                return;
            }

            for (RouteData parent : routeDataList) {
                String id = String.valueOf(parent.getId());
                if (id != null && id.equals(parentId)) {
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