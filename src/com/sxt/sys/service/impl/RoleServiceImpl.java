package com.sxt.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Menu;
import com.sxt.sys.domain.Role;
import com.sxt.sys.mapper.MenuMapper;
import com.sxt.sys.mapper.RoleMapper;
import com.sxt.sys.service.MenuService;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.MenuVo;
import com.sxt.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    RoleMapper roleMapper;


    @Override
    public List<Role> queryAllRoleForList(RoleVo roleVo) {
        return null;
    }

    @Override
    public List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId) {
        return null;
    }

    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        List<Role> data = roleMapper.queryAllRole(roleVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addRole(RoleVo roleVo) {
        roleMapper.insertSelective(roleVo);
    }

    @Override
    public void updateRole(RoleVo roleVo) {
        roleMapper.updateByPrimaryKeySelective(roleVo);
    }

    @Override
    public Integer queryRoleByPid(Integer pid) {
        return null;
    }

    @Override
    public void deleteRole(Integer[] ids) {
        // 删除角色表的数据
        roleMapper.deleteRoleByRid(ids);
        // 根据角色id删除sys_role_menu里面的数据
        roleMapper.deleteRoleMenuByRid(ids);
        // 根据角色id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByRid(ids);

    }


}
