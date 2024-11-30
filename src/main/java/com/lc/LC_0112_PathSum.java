package com.lc;

public class LC_0112_PathSum {
  public boolean hasPathSum(TreeNode root, int sum) {
    return pathExist(root, sum, 0);
  }

  private boolean pathExist(TreeNode root, int sum, int k) {
    if (root == null) {
      return false;
    }
    k = k + root.val;
    if (root.left == null && root.right == null) {
      if (sum == k) {
        return true;
      } else {
        return false;
      }
    }
    return pathExist(root.left, sum, k) || pathExist(root.right, sum, k);
  }
}
