package com.sxt.sys.mapper;

import com.sxt.sys.domain.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 查询角色
     */
    List<Role> queryAllRole(Role role);
    
    void deleteRoleByRid(Integer[] ids);
    
    void deleteRoleMenuByRid(Integer[] ids);
    
    void deleteRoleUserByRid(Integer[] ids);
}