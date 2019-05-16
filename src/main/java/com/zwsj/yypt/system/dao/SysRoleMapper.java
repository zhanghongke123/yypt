package com.zwsj.yypt.system.dao;

import com.zwsj.yypt.common.config.MyMapper;
import com.zwsj.yypt.system.domain.SysMenu;
import com.zwsj.yypt.system.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-01
 * @描述
 */
@Mapper
public interface SysRoleMapper extends MyMapper<SysRole> {



    List<SysRole> getUserRoles(Long userId);

}
