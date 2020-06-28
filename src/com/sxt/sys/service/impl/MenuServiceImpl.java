package com.sxt.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Menu;
import com.sxt.sys.mapper.MenuMapper;
import com.sxt.sys.service.MenuService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> queryAllMenuForList(MenuVo menuVo) {
        return menuMapper.queryAllMenu(menuVo);
    }

    /**
     * 后期权限管理完成之后再来改
     */
    @Override
    public List<Menu> queryMenuByUserIdForList(MenuVo menuVo, Integer userId) {
        return menuMapper.queryAllMenu(menuVo);
    }

    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        Page<Object> page = PageHelper.startPage(menuVo.getPage(), menuVo.getLimit());
        List<Menu> data = menuMapper.queryAllMenu(menuVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addMenu(MenuVo menuVo) {
        menuMapper.insertSelective(menuVo);
    }

    @Override
    public void updateMenu(MenuVo menuVo) {
        menuMapper.updateByPrimaryKeySelective(menuVo);
    }

    @Override
    public Integer queryMenuByPid(Integer pId) {
        return menuMapper.queryMenuByPid(pId);
    }

    @Override
    public void deleteMenu(MenuVo menuVo) {

        // 先删除菜单表里的数据
        menuMapper.deleteByPrimaryKey(menuVo.getId());

        // 再根据菜单id删除中间表sys_role_menu里面的数据
        menuMapper.deleteRoleMenuByMid(menuVo.getId());
    }

}
