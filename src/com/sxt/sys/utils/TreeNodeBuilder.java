package com.sxt.sys.utils;

import com.sxt.sys.constant.SysConstant;
import com.sxt.sys.domain.Menu;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {


    /**
     * 把简单的集合转换成有层级关系的集合
     *
     * @param nodes
     * @param topPid 根节点
     * @return
     */
    public static List<TreeNode> builder(List<TreeNode> nodes, Integer topPid) {
        List<TreeNode> treeNodes = new ArrayList<>();
        for (TreeNode n1 : nodes) {
            if (topPid.equals(n1.getPid())) {
                treeNodes.add(n1);
            }
            for (TreeNode n2 : nodes) {
                if (n1.getId().equals(n2.getPid())) {
                    n1.getChildren().add(n2);
                }
            }
        }
        return treeNodes;
    }


    /**
     * 把list的数据转换成TreeNode的数据
     *
     * @param list
     * @return
     */
    public static List<TreeNode> list2Nodes(List<Menu> list) {
        List<TreeNode> nodes = new ArrayList<>();
        for (Menu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = SysConstant.SPREAD_TRUE.equals(menu.getSpread());
            String target = menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        return nodes;
    }

}
