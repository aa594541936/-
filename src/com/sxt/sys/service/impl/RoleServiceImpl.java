package com.sxt.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.constant.SysConstant;
import com.sxt.sys.domain.Menu;
import com.sxt.sys.domain.Role;
import com.sxt.sys.mapper.MenuMapper;
import com.sxt.sys.mapper.RoleMapper;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.TreeNode;
import com.sxt.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    MenuMapper menuMapper;

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

    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleId) {
        // 查询所有可用的菜单
        Menu menu = new Menu();
        menu.setAvailable(SysConstant.AVAILABLE_TRUE);
        List<Menu> allMenu = menuMapper.queryAllMenu(menu);
        
        // 根据角色ID查询当前角色拥有的菜单
        List<Menu> roleMenu = menuMapper.queryMenuByRoleId(roleId, SysConstant.AVAILABLE_TRUE);
        
        List<TreeNode> data = new ArrayList<>();

        for (Menu m1 : allMenu) {
            String checkArr = SysConstant.CODE_ZERO + "";
            for (Menu m2 : roleMenu) {
                if (m1.getId().equals(m2.getId())) {
                    checkArr = SysConstant.CODE_ONE + "";
                    break;
                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            Boolean spread = SysConstant.SPREAD_TRUE.equals(m1.getSpread());
            data.add(new TreeNode(id, pid, title, spread, checkArr));
        }
        
        return new DataGridView(data);
    }

    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        
        Integer roleId = roleVo.getRoleid();
        Integer[] roleId2 = new Integer[]{roleId};
        Integer[] mIds = roleVo.getIds();
        
        // 每次保存角色和菜单之间的关系之前先把sys_role_menu里面的该角色的所有菜单删除，不然会出现主键冲突
        roleMapper.deleteRoleMenuByRid(roleId2);
        
        // 保存角色和菜单之间的关系
        // 在for循环里面执行SQL不是很好，可以优化
        if (mIds != null) {
            for (Integer mId : mIds) {
                roleMapper.insertRoleMenu(roleId, mId);
            }
        }
    }


}
