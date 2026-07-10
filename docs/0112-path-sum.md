---
description: EASY
---

# 112. Path Sum

Given the root of a binary tree and an integer `targetSum`, return `true` if the tree has a root-to-leaf path such that adding up all the values along the path equals `targetSum`.

A leaf is a node with no children.

**Example 1:**

```text
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path 5 -> 4 -> 11 -> 2 has sum 22.
```

**Example 2:**

```text
Input: root = [1,2,3], targetSum = 5
Output: false
```

**Example 3:**

```text
Input: root = [], targetSum = 0
Output: false
```

**Constraints:**

- The number of nodes in the tree is in the range `[0, 5000]`.
- `-1000 <= Node.val <= 1000`
- `-1000 <= targetSum <= 1000`

---

## Approach: Recursive DFS with Running Sum

Carry a running sum `k` down the tree. At each leaf, check whether `k` equals the target. Recurse into both subtrees and return `true` if either path reaches the target.

#### Complexity Analysis

- **Time complexity: O(n).** In the worst case every node is visited (e.g., no valid path exists).
- **Space complexity: O(h).** The recursion stack depth is bounded by the tree height.

```java
package com.lc;

public class LC_0112_PathSum {
  public boolean hasPathSum(TreeNode root, int sum) {
    return pathExist(root, sum, 0);
  }

  private boolean pathExist(TreeNode root, int sum, int k) {
    if (root == null) {
      return false;
    }
    k = k + root.val;
    if (root.left == null && root.right == null) {
      if (sum == k) {
        return true;
      } else {
        return false;
      }
    }
    return pathExist(root.left, sum, k) || pathExist(root.right, sum, k);
  }
}
```

**Key insight:** Checking the sum only at leaf nodes (both children null) ensures intermediate nodes with a matching partial sum do not generate false positives.
