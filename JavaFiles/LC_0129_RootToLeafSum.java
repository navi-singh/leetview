public class LC_0129_RootToLeafSum {
  public int sumNumbers(TreeNode root) {
    int[] sum = new int[1];
    helper(root, sum, 0);
    return sum[0];
  }

  private void helper(TreeNode root, int[] sum, int current) {
    if (root == null) {
      return;
    }
    current = current * 10 + root.val;
    if (root.left == null && root.right == null) {
      sum[0] += current;
    }
    helper(root.left, sum, current);
    helper(root.right, sum, current);
  }
}
