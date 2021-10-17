/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is
 * closest to the target. Note:
 *
 * <p>Given target value is a floating point. You are guaranteed to have only one unique value in
 * the BST that is closest to the target.
 */
public class LC270_ClosestBSTValue {
  public int closestValue(TreeNode root, double target) {
    int diff, closest;
    diff = closest = Integer.MAX_VALUE;
    while (root != null) {
      if (root.val - target < diff) {
        diff = root.val - target;
        closest = root.val;
      }
      if (root.val == target) {
        break;
      } else if (root.val < target) {
        root = root.right;
      } else {
        root = root.left;
      }
    }
    return closest;
  }
}
