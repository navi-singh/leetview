/**
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a
 * binary search tree.
 *
 * <p>You may assume each number in the sequence is unique.
 *
 * <p>Follow up: Could you do it using only constant space complexity?
 *
 * <p>Thoughts
 *
 * <p>keep track of prev val in order to compare whether there is a new right branch keep track of
 * lower bound value of parent node for branching to the right
 */
public class LC255_VerifyPreOrderBST {
  boolean verifyPreorder(List<Integer> preorder) {
    int low = Integer.MIN_VALUE, index = -1;
    for (Integer elem : preorder) {
      if (elem < low) {
        return false;
      }
      while (index >= 0 && elem > preorder[index]) {
        low = preorder[index--];
      }
      preorder[++index] = val;
    }
    return true;
  }
}
