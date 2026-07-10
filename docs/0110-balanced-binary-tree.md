---
description: EASY
---

# 110. Balanced Binary Tree

Given a binary tree, determine if it is height-balanced.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

**Example 1:**

```text
Input: root = [3,9,20,null,null,15,7]
Output: true
```

**Example 2:**

```text
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false
```

**Example 3:**

```text
Input: root = []
Output: true
```

**Constraints:**

- The number of nodes in the tree is in the range `[0, 5000]`.
- `-10^4 <= Node.val <= 10^4`

---

## Approach: Bottom-Up DFS with Sentinel Value

Compute heights bottom-up using a helper that returns `-1` as a sentinel to signal that a subtree is already unbalanced. If either child returns `-1`, or if the height difference exceeds 1, propagate `-1` upward immediately without further work.

#### Complexity Analysis

- **Time complexity: O(n).** Each node is visited exactly once; unbalanced subtrees short-circuit early.
- **Space complexity: O(h).** The call stack depth is bounded by the tree height.

```java
package com.lc;

public class LC_0110_BalancedBST {
  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (helper(root) == -1) {
      return false;
    }
    return true;
  }

  public int helper(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = helper(root.left);
    int right = helper(root.right);
    if (left == -1 || right == -1) {
      return -1;
    }
    if (Math.abs(left - right) > 1) {
      return -1;
    }
    return Math.max(left, right) + 1;
  }
}
```

**Key insight:** Combining height computation with balance checking in one pass avoids the O(n^2) penalty of a naive two-pass approach that calls a separate height function at every node.
