package com.zwsj.yypt.common.utils;


import com.zwsj.yypt.common.domain.EleTreeData;
import com.zwsj.yypt.common.domain.YyptResponse;
import com.zwsj.yypt.common.domain.route.Meta;
import com.zwsj.yypt.common.domain.route.RouteData;
import com.zwsj.yypt.common.domain.route.YyptButton;
import com.zwsj.yypt.system.domain.SysMenu;
import com.zwsj.yypt.system.domain.SysMenuButton;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class TreeUtil {


    private final static Long TOP_NODE_ID = 0L;

    /**
     * 构建路由树
     * @param routes
     * @return
     */
    public static List<RouteData> buildVueRouter(List<SysMenu> routes, List<SysMenuButton> buttons) {
        if (routes == null) {
            return null;
        }

        Map<Long,List<YyptButton>> menubuttonMap = new HashMap<>();
        buttons.forEach(button ->{
            Long menuId = button.getMenuId();
            List<YyptButton> buttonList =  menubuttonMap.get(menuId);
            if(CollectionUtils.isEmpty(buttonList)){
                buttonList = new ArrayList<>();
            }

            YyptButton yyptButton = new YyptButton();
            yyptButton.setIcon(button.getIcon());
            yyptButton.setMenuId(menuId);
            yyptButton.setName(button.getName());
            yyptButton.setPermission(button.getPermission());
            yyptButton.setRequestPath(button.getRequestPath());
            yyptButton.setOrderCode(button.getOrderCode());
            buttonList.add(yyptButton);
            menubuttonMap.put(menuId,buttonList);
        });


        List<RouteData> routeDataList = new ArrayList<RouteData>();
        routes.forEach(route ->{
                RouteData routeData = new RouteData();
                routeData.setId(route.getMenuId());
                routeData.setComponent(route.getComponent());
                Meta meta = new Meta();
                meta.setNoCache(route.getNocacheFlag() == 1 ? true : false);
                meta.setIcon(route.getIcon());
                meta.setTitle(route.getName());
                meta.setButtons(menubuttonMap.get(route.getMenuId()));
                routeData.setMeta(meta);
                routeData.setName(route.getComponentName());
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



    /**
     * 用于构建饿了吗树组件的数据
     * @return
     */
    public static <T> List<EleTreeData> buildElemTree(List<T> objectList,String idName,
                                                      String labeltemplate,String parentIdName) {
        if (CollectionUtils.isEmpty(objectList)) {
            return null;
        }

        List<EleTreeData> eleTreeDataList = new ArrayList<EleTreeData>();
        objectList.forEach(ele ->{
            EleTreeData eleTreeData = new EleTreeData();
            Long id = (Long) ReflexObjectUtil.getValueByKey(ele,idName);
            eleTreeData.setId(id);
            String label = TreeUtil.getLabel(labeltemplate,ele);
            eleTreeData.setLabel(label);
            Long parentId = (Long) ReflexObjectUtil.getValueByKey(ele,parentIdName);
            eleTreeData.setParentId(parentId);
            eleTreeDataList.add(eleTreeData);
        });

        List<EleTreeData> topData = new ArrayList<>();
        eleTreeDataList.forEach(data -> {
            Long parentId = data.getParentId();
            if (parentId == null || TOP_NODE_ID == parentId) {
                topData.add(data);
                return;
            }

            for (EleTreeData parent : eleTreeDataList) {
                Long id = parent.getId();
                if (id != null && id == parentId) {
                    if (parent.getChildren() == null){
                        parent.initChildren();
                    }
                    parent.getChildren().add(data);
                    return;
                }
            }
        });

        return topData;
    }

    public static String getLabel(String template, Object obj){
        Map<String,String> fileds = ReflexObjectUtil.getKeyAndStringValue(obj);
        Set<String> keys = fileds.keySet();
        for (String key : keys){
            template = template.replace("{"+key+"}",fileds.get(key));
            if(template.indexOf("{") == -1){
                break;
            }
        }
        return template;
    }

}