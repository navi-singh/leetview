package com.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC_0094_InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> st = new Stack<TreeNode>();
        while (root != null || !st.empty()) {
            if (root != null) {
                st.add(root);
                root = root.left;
            } else {
                root = st.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}