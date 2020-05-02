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
            list = this.menuService.queryAllMenuForList(menuVo);
        } else {
            // 如果是普通用户
            list = this.menuService.queryMenuByUserIdForList(menuVo, user.getUserid());
        }

        List<TreeNode> nodes = TreeNodeBuilder.list2Nodes(list);
        
//        List<TreeNode> nodes = new ArrayList<>();
//        
//        // 把list里的数据放到nodes
//        for (Menu menu : list) {
//            Integer id = menu.getId();
//            Integer pid = menu.getPid();
//            String title = menu.getTitle();
//            String icon = menu.getIcon();
//            String href = menu.getHref();
//            Boolean spread = SysConstant.SPREAD_TRUE.equals(menu.getSpread());
//            String target = menu.getTarget();
//            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
//        }
        
        return TreeNodeBuilder.builder(nodes, 1);
    }
    
    
    
    @RequestMapping("/loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo) {
        // 只查询可用的菜单
        menuVo.setAvailable(SysConstant.AVAILABLE_TRUE);
        
        List<Menu> list = this.menuService.queryAllMenuForList(menuVo);
        
        List<TreeNode> nodes = TreeNodeBuilder.list2Nodes(list);
        
//        List<TreeNode> nodes = new ArrayList<>();
//
//        // 把list里的数据放到nodes
//        for (Menu menu : list) {
//            Integer id = menu.getId();
//            Integer pid = menu.getPid();
//            String title = menu.getTitle();
//            String icon = menu.getIcon();
//            String href = menu.getHref();
//            Boolean spread = SysConstant.SPREAD_TRUE.equals(menu.getSpread());
//            String target = menu.getTarget();
//            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
//        }
        

        return new DataGridView(nodes);
    }


    @RequestMapping("/loadAllMenu")
    public DataGridView loadAllMenu(MenuVo menuVo) {
        return this.menuService.queryAllMenu(menuVo);
    }
    
    
    @RequestMapping("/addMenu")
    public ResultObj addMenu(MenuVo menuVo) {
        try {
            this.menuService.addMenu(menuVo);   
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    
}
