package com.sxt.sys.service;

import com.sxt.sys.domain.Menu;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.MenuVo;

import java.util.List;

public interface MenuService {

    /**
     * 查询所有菜单返回
     * List<Menu>
     */
    List<Menu> queryAllMenuForList(MenuVo menuVo);

    /**
     * 根据用户id查询用户的可用菜单
     */
    List<Menu> queryMenuByUserIdForList(MenuVo menuVo, Integer userId);

    /**
     * 查询所有菜单
     *
     * @param menuVo
     * @return
     */
    DataGridView queryAllMenu(MenuVo menuVo);

    /**
     * 添加菜单
     *
     * @param menuVo
     */
    void addMenu(MenuVo menuVo);

    /**
     * 修改菜单
     *
     * @param menuVo
     */
    void updateMenu(MenuVo menuVo);

    /**
     * 根据pId查询菜单数量
     *
     * @param pid
     * @return
     */
    Integer queryMenuByPid(Integer pid);


    void deleteMenu(MenuVo menuVo);
}
