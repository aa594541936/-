package com.sxt.sys.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器  作用：路由
 */
@Controller
@RequestMapping("/sys")
public class SysController {

    /**
     * 跳转菜单管理
     */
    @RequestMapping("/toMenuManager")
    public String toMenuManager() {
        return "system/menu/menuManager";
    }
    
    /**
     * 跳转菜单管理左边的的菜单树页面
     */
    @RequestMapping("/toMenuLeft")
    public String toMenuLeft() {
        return "system/menu/menuLeft";
    }
    
    /**
     * 跳转菜单管理右边的菜单列表
     */
    @RequestMapping("/toMenuRight")
    public String toMenuRight() {
        return "system/menu/menuRight";
    }

    /**
     * 跳转角色管理页面
     */
    @RequestMapping("/toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }

    @RequestMapping("/toUserManager")
    public String toUserManager() {
        return "system/user/userManager";
    }

}
