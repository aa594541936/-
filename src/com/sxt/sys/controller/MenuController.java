package com.sxt.sys.controller;

import com.sxt.sys.constant.SysConstant;
import com.sxt.sys.domain.Menu;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.MenuService;
import com.sxt.sys.utils.*;
import com.sxt.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单管理控制器
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;


    @RequestMapping("/loadIndexLeftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo) {

        // 得到当前登陆的用户对象
        User user = (User) WebUtils.getHttpSession().getAttribute("user");

        List<Menu> list = null;

        // 只查询可用的菜单
        menuVo.setAvailable(SysConstant.AVAILABLE_TRUE);

        // 判断当前登陆用户的角色权限
        if (SysConstant.AVAILABLE_TRUE.equals(user.getType())) {
            // 如果是超级管理员
            list = menuService.queryAllMenuForList(menuVo);
        } else {
            // 如果是普通用户
            list = menuService.queryMenuByUserIdForList(menuVo, user.getUserid());
        }

        List<TreeNode> nodes = TreeNodeBuilder.list2Nodes(list);

        return TreeNodeBuilder.builder(nodes, 1);
    }

    @RequestMapping("/loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo) {

        // 只查询可用的菜单
        menuVo.setAvailable(SysConstant.AVAILABLE_TRUE);

        List<Menu> list = menuService.queryAllMenuForList(menuVo);

        System.out.println("=======================：" + list.toString());

        List<TreeNode> nodes = TreeNodeBuilder.list2Nodes(list);

        return new DataGridView(nodes);
    }

    @RequestMapping("/loadAllMenu")
    public DataGridView loadAllMenu(MenuVo menuVo) {
        return menuService.queryAllMenu(menuVo);
    }

    @RequestMapping("/addMenu")
    public ResultObj addMenu(MenuVo menuVo) {
        try {
            menuService.addMenu(menuVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("/updateMenu")
    public ResultObj updateMenu(MenuVo menuVo) {
        try {
            menuService.updateMenu(menuVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    @RequestMapping("/checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(MenuVo menuVo) {
        // 根据pid查询子菜单数量
        Integer count = menuService.queryMenuByPid(menuVo.getId());
        if (count > 0) {
            return ResultObj.STATUS_TRUE;
        } else {
            return ResultObj.STATUS_FALSE;
        }
    }

    @RequestMapping("/deleteMenu")
    public ResultObj deleteMenu(MenuVo menuVo) {
        try {
            menuService.deleteMenu(menuVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}
