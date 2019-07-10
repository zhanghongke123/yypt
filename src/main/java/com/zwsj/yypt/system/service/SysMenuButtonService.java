package com.zwsj.yypt.system.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zwsj.yypt.system.domain.SysMenuButton;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-07-08
 * @描述
 */
public interface SysMenuButtonService extends IService<SysMenuButton> {
    List<SysMenuButton> getMenuButtonByRoleId(Long roleId) throws Exception;
    void saveRoleButtons(Long roleId, Long menuId, JSONArray array) throws Exception;
    void delete(Long roleId) throws Exception ;
}
