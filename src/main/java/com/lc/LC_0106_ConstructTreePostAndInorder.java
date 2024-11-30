package com.lc;

public class LC_0106_ConstructTreePostAndInorder {
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    int inStart = 0;
    int inEnd = inorder.length - 1;
    int postStart = 0;
    int postEnd = postorder.length - 1;

    return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
  }

  public TreeNode buildTree(
      int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
    if (inStart > inEnd || postStart > postEnd) return null;

    int rootValue = postorder[postEnd];
    TreeNode root = new TreeNode(rootValue);

    int inIndex = 0;
    for (int i = 0; i < inorder.length; i++) {
      if (inorder[i] == rootValue) {
        inIndex = i;
        break;
      }
    }

    root.left =
        buildTree(
            inorder,
            inStart,
            inIndex - 1,
            postorder,
            postStart,
            postStart + inIndex - (inStart + 1));
    root.right =
        buildTree(
            inorder, inIndex + 1, inEnd, postorder, postStart + inIndex - inStart, postEnd - 1);
    return root;
  }
}
