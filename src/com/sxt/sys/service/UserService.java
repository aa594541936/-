package com.sxt.sys.service;

import com.sxt.sys.domain.User;
import com.sxt.sys.vo.UserVo;

public interface UserService {

    User login(UserVo userVo);

}
