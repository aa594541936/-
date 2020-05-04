package com.sxt.sys.mapper;

import com.sxt.sys.domain.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 查询所有菜单
     */
    List<Menu> queryAllMenu(Menu menu);

    /**
     * 根据pid查询子菜单数量
     * @param pid
     * @return
     */
    Integer queryMenuByPid(Integer pid);

    
    void deleteRoleMenuByMid(Integer mid);
}