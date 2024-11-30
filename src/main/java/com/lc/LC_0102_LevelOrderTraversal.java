package com.lc;

import java.util.*;
import java.util.ArrayList;

class LC102_LevelOrderTraversal {
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    int levelSize = 1;
    List<Integer> tempList = new ArrayList<Integer>();
    queue.add(root);
    while (queue.size() > 0) {
      TreeNode elem = queue.remove();
      tempList.add(elem.val);
      levelSize--;
      if (elem.left != null) {
        queue.add(elem.left);
      }
      if (elem.right != null) {
        queue.add(elem.right);
      }
      if (levelSize == 0) {
        res.add(new ArrayList(tempList));
        tempList.clear();
        levelSize = queue.size();
      }
    }
    return res;
  }
}
