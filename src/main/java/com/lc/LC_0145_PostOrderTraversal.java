package com.lc;

import java.util.List;
import java.util.ArrayList;
public class LC_0145_PostOrderTraversal {
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    postorderTraversal(root, res);
    return res;
  }

  private void postorderTraversal(TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }
    postorderTraversal(root.left, res);
    postorderTraversal(root.right, res);
    res.add(root.val);
  }
}
