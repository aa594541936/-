package com.sxt.sys.controller;

import com.sxt.sys.constant.SysConstant;
import com.sxt.sys.domain.Menu;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.MenuService;
import com.sxt.sys.utils.TreeNode;
import com.sxt.sys.utils.TreeNodeBuilder;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        // 只查询可用的
        menuVo.setAvailable(SysConstant.AVAILABLE_TRUE);
        // 如果是超级管理员
        if (SysConstant.AVAILABLE_TRUE.equals(user.getType())) {
            list = this.menuService.queryAllMenuForList(menuVo);
        } else {
            list = this.menuService.queryMenuByUserIdForList(menuVo, user.getUserid());
        }
        
        List<TreeNode> nodes = new ArrayList<>();
        
        // 把list里的数据放到nodes
        for (Menu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = SysConstant.SPREAD_TRUE.equals(menu.getSpread());
            String target = menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        return TreeNodeBuilder.builder(nodes, 1);
    }
    
}
