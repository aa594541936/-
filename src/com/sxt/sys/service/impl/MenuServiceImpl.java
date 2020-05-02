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
        Page<Object> page= PageHelper.startPage(menuVo.getPage(), menuVo.getLimit());
        List<Menu> data = this.menuMapper.queryAllMenu(menuVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addMenu(MenuVo menuVo) {
        this.menuMapper.insertSelective(menuVo);
    }
    
}
