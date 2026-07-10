---
description: HARD
---

# 124. Binary Tree Maximum Path Sum

A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

**Example 1:**

```text
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
```

**Example 2:**

```text
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
```

**Constraints:**

- The number of nodes in the tree is in the range `[1, 3 * 10^4]`.
- `-1000 <= Node.val <= 1000`

---

## Approach: Post-Order DFS with Global Maximum

For each node, compute the maximum gain achievable by extending a path upward through it (either the node alone, or the node plus its best child). Separately, consider the path that goes through the node as a "turning point" (left + node + right) and update a global maximum. Return only the single-arm gain to the parent, since a path cannot branch.

#### Complexity Analysis

- **Time complexity: O(n).** Every node is visited once in the post-order traversal.
- **Space complexity: O(h).** Recursion stack depth equals the tree height.

```java
package com.lc;

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
```

**Key insight:** The helper returns the best single-arm extension (node + at most one child) so the parent can extend the path further, while the full through-path (left + node + right) is only considered for updating the global maximum — these two concerns must be tracked separately.
