package com.sxt.sys.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {


    /**
     * 把简单的集合转换成有层级关系的集合
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
    
}
