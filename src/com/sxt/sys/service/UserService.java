package com.sxt.sys.service;

import com.sxt.sys.domain.User;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.UserVo;

public interface UserService {

    User login(UserVo userVo);

    /**
     * 查询所有用户
     * @param userVo
     * @return
     */
    DataGridView queryAllUser(UserVo userVo);

    /**
     * 添加用户
     * @param userVo
     */
    void addUser(UserVo userVo);

    /**
     * 修改用户
     * @param userVo
     */
    void updateUser(UserVo userVo);

    /**
     * 根据id删除用户
     * @param userId
     */
    void deleteUser(Integer userId);
    
    /**
     * 批量删除用户
     * @param ids
     */
    void deleteBatchUser(Integer[] ids);

    /**
     * 重置密码
     * @param userid
     */
    void resetUserPwd(Integer userid);

    /**
     *  加载用户管理的分配角色的数据
     * @param userid
     * @return
     */
    DataGridView queryUserRole(Integer userId);

    /**
     * 保存用户和角色的关系
     * @param userVo
     */
    void saveUserRole(UserVo userVo);

}
