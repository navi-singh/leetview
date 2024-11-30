package com.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LC_0103_ZigZagTraversal {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    List<Integer> tempList = new ArrayList<Integer>();
    int levelSize = 1;
    queue.add(root);
    while (queue.size() > 0) {
      TreeNode node = queue.pop();
      tempList.add(node.val);
      levelSize--;
      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
      if (levelSize == 0) {
        if (res.size() % 2 == 1) {
          Collections.reverse(tempList);
        }
        res.add(new ArrayList<Integer>(tempList));
        tempList.clear();
        levelSize = queue.size();
      }
    }
    return res;
  }
}
