package com.lc;

public class LC_0098_ValidateBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    private boolean helper(TreeNode root, double min, double max) {
        if (root == null) {
            return true;
        }
        if (!(root.val > min && root.val < max)) {
            return false;
        }
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}