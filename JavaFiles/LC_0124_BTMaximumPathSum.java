public class LC_0124_BTMaximumPathSum {
  public int maxPathSum(TreeNode root) {
    int[] max = new int[1];
    max[0] = Integer.MIN_VALUE;
    helper(root, max);
    return max[0];
  }

  private int helper(TreeNode root, int[] maxSoFar) {
    if (root == null) {
      return 0;
    }
    int left = helper(root.left, maxSoFar);
    int right = helper(root.right, maxSoFar);
    int temp = Math.max(root.val, Math.max(root.val + left, root.val + right));

    maxSoFar[0] = Math.max(maxSoFar[0], Math.max(temp, left + root.val + right));
    return temp;
  }
}
