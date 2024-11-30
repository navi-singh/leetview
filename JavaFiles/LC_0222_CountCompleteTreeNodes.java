public class LC_0222_CountCompleteTreeNodes {
  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = getLeftHeight(root) + 1;
    int right = getRightHeight(root) + 1;
    if (left == right) {
      return (2 << (left - 1)) - 1;
    } else {
      return countNodes(root.left) + countNodes(root.right) + 1;
    }
  }

  private int getLeftHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int height = 0;
    while (root.left != null) {
      root = root.left;
      height++;
    }
    return height;
  }

  private int getRightHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int height = 0;
    while (root.right != null) {
      root = root.right;
      height++;
    }
    return height;
  }
}
