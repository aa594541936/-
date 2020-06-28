package com.sxt.sys.service;

import com.sxt.sys.domain.Menu;
import com.sxt.sys.domain.Role;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.MenuVo;
import com.sxt.sys.vo.RoleVo;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有菜单返回
     * List<Menu>
     */
    List<Role> queryAllRoleForList(RoleVo roleVo);

    /**
     * 根据用户id查询用户的可用菜单
     */
    List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId);

    /**
     * 查询所有菜单
     *
     * @param roleVo
     * @return
     */
    DataGridView queryAllRole(RoleVo roleVo);

    /**
     * 添加菜单
     *
     * @param roleVo
     */
    void addRole(RoleVo roleVo);

    /**
     * 修改菜单
     *
     * @param roleVo
     */
    void updateRole(RoleVo roleVo);

    /**
     * 根据pId查询菜单数量
     *
     * @param pid
     * @return
     */
    Integer queryRoleByPid(Integer pid);


    void deleteRole(Integer[] ids);

    DataGridView initRoleMenuTreeJson(Integer roleId);

    void saveRoleMenu(RoleVo roleVo);

}
