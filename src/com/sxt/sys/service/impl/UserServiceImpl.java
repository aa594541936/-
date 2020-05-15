package com.sxt.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.constant.SysConstant;
import com.sxt.sys.domain.Role;
import com.sxt.sys.domain.User;
import com.sxt.sys.mapper.RoleMapper;
import com.sxt.sys.mapper.UserMapper;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User login(UserVo userVo) {
        // 明文123456
        // 生成密文
        String pwd = DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        userVo.setPwd(pwd);
        return userMapper.login(userVo);
    }

    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        List<User> data = userMapper.queryAllUser(userVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addUser(UserVo userVo) {
        // 设置默认密码
        userVo.setPwd(DigestUtils.md5DigestAsHex(SysConstant.USER_DEFAULT_PWD.getBytes()));
        // 设置用户类型 都是普通用户 type=2
        userVo.setType(SysConstant.USER_TYPE_NORMAL);
        userMapper.insertSelective(userVo);
    }

    @Override
    public void updateUser(UserVo userVo) {
        userMapper.updateByPrimaryKeySelective(userVo);
    }

    @Override
    public void deleteUser(Integer[] ids) {
        // 删除用户
        userMapper.deleteUserByUid(ids);
        // 根据用户id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByUid(ids);
    }

    @Override
    public void deleteBatchUser(Integer[] ids) {

    }

    @Override
    public void resetUserPwd(Integer userid) {

    }

    @Override
    public DataGridView queryUserRole(Integer userId) {
        Role role = new Role();
        role.setAvailable(SysConstant.AVAILABLE_TRUE);

        List<Role> allRole = roleMapper.queryAllRole(role);

        List<Role> roles = roleMapper.queryRoleByUid(SysConstant.AVAILABLE_TRUE, userId);

        List<Map<String, Object>> data = new ArrayList<>();

        for (Role r1 : allRole) {
            boolean LAY_CHECKED = false;
            for (Role r2 : roles) {
                if (r1.getRoleid().equals(r2.getRoleid())) LAY_CHECKED = true;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("roleid", r1.getRoleid());
            map.put("rolename", r1.getRolename());
            map.put("roledesc", r1.getRoledesc());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(data);
    }

    @Override
    public void saveUserRole(UserVo userVo) {

    }
}
