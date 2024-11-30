package com.lc;

public class LC_0236_LCABinaryTree {
  TreeNode res = null;

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    helper(root, p, q);
    return this.res;
  }

  public int helper(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return 0;
    }

    int left = helper(root.left, p, q);
    int right = helper(root.right, p, q);
    int cur = (root == p || root == q) ? 1 : 0;
    int totalFind = left + right + cur;
    if (totalFind >= 2 && res == null) {
      this.res = root;
    }
    return totalFind;
  }
}
