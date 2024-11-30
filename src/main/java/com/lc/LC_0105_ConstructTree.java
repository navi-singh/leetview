package com.lc;

public class LC_0105_ConstructTree {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    int inStart = 0, inEnd = inorder.length - 1;
    int preStart = 0, preEnd = preorder.length - 1;
    return helper(preorder, preStart, preEnd, inorder, inStart, inEnd);
  }

  private TreeNode helper(
      int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
    if (inStart > inEnd || preStart > preEnd) {
      return null;
    }
    int val = preorder[preStart];
    TreeNode node = new TreeNode(val);
    int inIndex = 0;
    for (int i = 0; i < inorder.length; i++) {
      if (val == inorder[i]) {
        inIndex = i;
        break;
      }
    }
    node.left =
        helper(
            preorder, preStart + 1, preStart + (inIndex - inStart), inorder, inStart, inIndex - 1);
    node.right =
        helper(preorder, preStart + (inIndex - inStart) + 1, preEnd, inorder, inIndex + 1, inEnd);
    return node;
  }
}
