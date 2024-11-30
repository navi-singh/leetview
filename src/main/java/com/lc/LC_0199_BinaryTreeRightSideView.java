package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0199_BinaryTreeRightSideView {
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    // if (root == null) {
    // return res;
    // }
    helper(root, res);
    return res;
  }

  private void helper(TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }
    res.add(root.val);
    if (root.right != null) {
      helper(root.right, res);
    } else {
      helper(root.left, res);
    }
  }
}
