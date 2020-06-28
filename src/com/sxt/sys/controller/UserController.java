package com.sxt.sys.controller;

import com.sxt.sys.constant.SysConstant;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {
        return userService.queryAllUser(userVo);
    }


    @RequestMapping("/addUser")
    public ResultObj addUser(UserVo userVo) {
        try {
            userService.addUser(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("/updateUser")
    public ResultObj updateUser(UserVo userVo) {
        try {
            userService.updateUser(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    @RequestMapping("/deleteUser")
    public ResultObj deleteUser(UserVo userVo) {
        try {
            userService.deleteUser(userVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("initUserRole")
    public DataGridView initUserRole(UserVo userVo) {
        return userService.queryUserRole(userVo.getUserid());
    }

}
