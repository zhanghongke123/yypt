package com.zwsj.yypt.system.dao;

import com.zwsj.yypt.common.config.YyptMapper;
import com.zwsj.yypt.system.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-02
 * @描述
 */
@Mapper
public interface SysMenuMapper extends YyptMapper<SysMenu> {
    List<SysMenu> getMenusByRoleId(Long roleId);

}
