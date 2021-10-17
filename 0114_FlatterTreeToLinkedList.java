public class LC114_FlatterTreeToLinkedList {
  public void flatten(TreeNode root) {
    process(root);
  }

  public TreeNode process(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode lTail = process(root.left);
    TreeNode rTail = process(root.right);
    if (root.left != null) {
      TreeNode temp = root.right;
      root.right = root.left;
      lTail.right = temp;
      root.left = null;
    }
    if (rTail != null) {
      return rTail;
    }
    if (lTail != null) {
      return lTail;
    }
    return root;
  }
}
