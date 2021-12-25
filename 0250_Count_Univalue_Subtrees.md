Count Univalue Subtrees

Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example
Example1

Input:  root = {5,1,5,5,5,#,5}
Output: 4
Explanation:
              5
             / \
            1   5
           / \   \
          5   5   5
Example2

Input:  root = {1,3,2,4,5,#,6}
Output: 3
Explanation:
              1
             / \
            3   2
           / \   \
          4   5   6

Time: O(n)
Space: O(h)
```Java

class Solution {
  public int countUnivalSubtrees(TreeNode root) {
    isUnival(root, Integer.MAX_VALUE);
    return ans;
  }

  private int ans = 0;

  private boolean isUnival(TreeNode root, int val) {
    if (root == null)
      return true;

    if (isUnival(root.left, root.val) & isUnival(root.right, root.val)) {
      ++ans;
      return root.val == val;
    }

    return false;
  }
}
```
