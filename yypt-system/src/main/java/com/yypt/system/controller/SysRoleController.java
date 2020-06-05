package com.yypt.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yypt.common.domain.EleTreeData;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.domain.YyptResponse;
import com.yypt.common.enums.ResultEnum;
import com.yypt.common.utils.TreeUtil;
import com.yypt.system.domain.*;
import com.yypt.system.service.SysMenuButtonService;
import com.yypt.system.service.SysMenuService;
import com.yypt.system.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 zhk
 * @创建时间 2019-04-07
 * @描述
 */
@RestController
@RequestMapping("sysrole")
@Validated
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;


    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    SysMenuButtonService sysMenuButtonService;



    @RequestMapping("/list")
    public YyptResponse list(@RequestBody SysRole sysRole){
       List<SysRole> data = sysRoleService.list(sysRole);
       return YyptResponse.success(data);
    }

    @RequestMapping("/save")
    public YyptResponse save(@RequestBody SysRole sysRole){
        return YyptResponse.success(sysRoleService.updateOrAdd(sysRole));
    }

    @RequestMapping("/del")
    public YyptResponse del(@RequestBody SysRole sysRole) throws Exception{
        sysRoleService.del(sysRole);
        return YyptResponse.success("删除成功");
    }




    @RequestMapping("/menutree")
    public YyptResponse menuTree(){
        JSONObject jsonObject = new JSONObject();
        //构建所有的菜单
        List<SysMenu> menuList = sysMenuService.getlist();
        jsonObject.put("allmenu",menuList);

        List<EleTreeData> treeData = TreeUtil.buildElemTree(menuList,
                "menuId","{name}({menuId})","parentid");
        jsonObject.put("treedata",treeData);


        //构建所有菜单所对应的按钮
        List<SysMenuButton> sysMenuButtonlist = sysMenuButtonService.list();
        jsonObject.put("allbutton",this.getMenuButton(sysMenuButtonlist));

        return YyptResponse.success(jsonObject);
    }



    @RequestMapping("/saveRoleMenu")
    public YyptResponse saveRoleMenu(@RequestBody JSONObject param){
        String roleId = param.getString("roleId");
        if(StringUtils.isEmpty(roleId)){
          return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"角色ID不能为空");
        }
        String menuids = param.getString("menuIds");
        sysMenuService.saveRoleMenu(Long.valueOf(roleId),menuids);
        return YyptResponse.success("修改成功");
    }

    @RequestMapping("/getRoleButtons")
    public YyptResponse getRoleButton(@RequestBody JSONObject parpam) throws Exception{
        String roleId = parpam.getString("roleId");
        if(StringUtils.isEmpty(roleId)){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"角色ID不能为空");
        }
        List<SysMenuButton> menuButtons = sysMenuButtonService.getMenuButtonByRoleId(Long.valueOf(roleId));
        return YyptResponse.success(this.getMenuButton(menuButtons));
    }


    @RequestMapping("/saveRoleButtons")
    public YyptResponse saveRoleButton(@RequestBody JSONObject param) throws Exception{
        String roleId = param.getString("roleId");
        if(StringUtils.isEmpty(roleId)){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"角色ID不能为空");
        }

        String menuId = param.getString("menuId");
        if(StringUtils.isEmpty(menuId)){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"菜单ID不能为空");
        }

        JSONArray array =  param.getJSONArray("buttonIds");
        sysMenuButtonService.saveRoleButtons(Long.parseLong(roleId), Long.parseLong(menuId),
                array);


        return YyptResponse.success("保存成功");
    }


    @RequestMapping("/getRoleUsers")
    public YyptResponse getRoleUsers(@RequestBody JSONObject param){
        String roleId = param.getString("roleId");
        if(StringUtils.isEmpty(roleId)){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"角色ID不能为空");
        }
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(Long.parseLong(roleId));
        return YyptResponse.success(sysRoleService.getRoleUsers(sysRole));
    }

    @RequestMapping("/userRep")
    public YyptResponse getUsers(@RequestBody QueryRequest<SysUser> request){
        Long queryRoleId = request.getQuerylist().getQueryRoleId();
        if(queryRoleId == null){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"queryRoleId不能为空");
        }
        return YyptResponse.success(sysRoleService.userRep(request));
    }


    @RequestMapping("/saverRoleUser")
    public YyptResponse saverRoleUser(@RequestBody JSONObject jsonObject){
        String roleId = jsonObject.getString("roleId");
        String userIds = jsonObject.getString("userIds");
        if(StringUtils.isBlank(roleId)){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"roleId不能为空");
        }

        if(StringUtils.isBlank(userIds)){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"userIds 数组不能为空");
        }
        sysRoleService.saverRoleUser(Long.parseLong(roleId), userIds);
        return YyptResponse.success("授权成功");
    }

    @RequestMapping("/delRoleUser")
    public YyptResponse delRoleUser(@RequestBody SysRoleUser sysRoleUser) throws Exception{
        Long roleUserId = sysRoleUser.getRoleUserId();
        if( roleUserId == null){
            return YyptResponse.failure(ResultEnum.PARAMETER_ERROR,"角色用户ID不能为空");
        }
        sysRoleService.delRoleUser(sysRoleUser);
        return YyptResponse.success("删除成功");
    }



    /**
     * 将menu转化为{'menuid':[button数组]}
     * @param sysMenuButtons
     * @return
     */
    private Map<Long,List<SysMenuButton>> getMenuButton(List<SysMenuButton> sysMenuButtons){
        Map<Long,List<SysMenuButton>> menubuttonMap = new HashMap<>();
        sysMenuButtons.forEach(button -> {
            Long menuId = button.getMenuId();
            List<SysMenuButton> buttonList =  menubuttonMap.get(menuId);
            if(CollectionUtils.isEmpty(buttonList)){
                buttonList = new ArrayList<>();
            }
            buttonList.add(button);
            menubuttonMap.put(menuId,buttonList);
        });

        return menubuttonMap;
    }




}
