package com.sxt.sys.controller;

import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;


    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {
        return userService.queryAllUser(userVo);
    }
    
}
