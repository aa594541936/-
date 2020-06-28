package com.sxt.sys.controller;

import com.sxt.sys.service.RoleService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * 加载角色列表返回DataGridView
     */
    @RequestMapping("/loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo) {
        return roleService.queryAllRole(roleVo);
    }

    /**
     * 添加角色
     */
    @RequestMapping("/addRole")
    public ResultObj addRole(RoleVo roleVo) {
        try {
            roleService.addRole(roleVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改角色
     */
    @RequestMapping("/updateRole")
    public ResultObj updateRole(RoleVo roleVo) {
        try {
            roleService.updateRole(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除角色
     */
    @RequestMapping("/deleteRole")
    public ResultObj deleteRole(RoleVo roleVo) {
        try {
            roleService.deleteRole(roleVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * 加载角色管理分配菜单的树的json
     */
    @RequestMapping("/initRoleMenuTreeJson")
    public DataGridView initRoleMenuTreeJson(Integer roleId) {
        return roleService.initRoleMenuTreeJson(roleId);
    }


    /**
     * 保存角色和菜单的关系
     */
    @RequestMapping("/saveRoleMenu")
    public ResultObj saveRoleMenu(RoleVo roleVo) {
        try {
            roleService.saveRoleMenu(roleVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }


}
