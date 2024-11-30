package com.lc;

import java.util.Stack;

public class LC_0173_BSTIterator {
  /**
   * Definition for a binary tree node. public class LC_0173_BSTIterator { int val; TreeNode left;
   * TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode
   * left, TreeNode right) { this.val = val; this.left = left; this.right = right; } }
   */
  class BSTIterator {
    Stack<TreeNode> st;

    public BSTIterator(TreeNode root) {
      st = new Stack<TreeNode>();
      while (root != null) {
        st.push(root);
        root = root.left;
      }
    }

    public int next() {
      TreeNode node = st.pop();
      int res = node.val;
      if (node.right != null) {
        node = node.right;
        while (node != null) {
          st.push(node);
          node = node.left;
        }
      }
      return res;
    }

    public boolean hasNext() {
      return !st.empty();
    }
  }

  /**
   * Your BSTIterator object will be instantiated and called as such: BSTIterator obj = new
   * BSTIterator(root); int param_1 = obj.next(); boolean param_2 = obj.hasNext();
   */
}
