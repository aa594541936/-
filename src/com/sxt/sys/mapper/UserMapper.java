package com.sxt.sys.mapper;

import com.sxt.sys.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 登陆
     */
    User login(User user);

    /**
     * 查询用户
     */
    List<User> queryAllUser(User user);

    void deleteUserByUid(Integer[] ids);
}