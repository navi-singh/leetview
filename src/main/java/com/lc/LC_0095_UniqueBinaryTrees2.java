package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0095_UniqueBinaryTrees2 {

  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return new ArrayList<TreeNode>();
    }
    return helper(1, n);
  }

  private List<TreeNode> helper(int m, int n) {
    List<TreeNode> res = new ArrayList<TreeNode>();
    if (m > n) {
      res.add(null);
      return res;
    }
    for (int i = m; i <= n; i++) {
      List<TreeNode> left = helper(m, i - 1);
      List<TreeNode> right = helper(i + 1, n);
      for (TreeNode l : left) {
        for (TreeNode r : right) {
          TreeNode curr = new TreeNode(i);
          curr.left = l;
          curr.right = r;
          res.add(curr);
        }
      }
    }
    return res;
  }
}
