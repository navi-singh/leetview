package com.lc;

import java.util.LinkedList;

public class LC_0230_KthSmallestBST {
  public int kthSmallest(TreeNode root, int k) {
    if (root == null) {
      return -1;
    }
    LinkedList<TreeNode> st = new LinkedList();
    while (true) {
      while (root != null) {
        st.add(root);
        root = root.left;
      }
      root = st.removeLast();
      if (--k == 0) {
        return root.val;
      }
      root = root.right;
    }
  }
}
