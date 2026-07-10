---
description: EASY
---

# 104. Maximum Depth of Binary Tree

Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

**Example 1:**

```text
Input: root = [3,9,20,null,null,15,7]
Output: 3
```

**Example 2:**

```text
Input: root = [1,null,2]
Output: 2
```

**Constraints:**

- The number of nodes in the tree is in the range `[0, 10^4]`.
- `-100 <= Node.val <= 100`

---

## Approach: Recursive DFS

Recursively compute the depth of the left and right subtrees and return the maximum of the two plus one (for the current node). The base case is a null node returning depth 0.

#### Complexity Analysis

- **Time complexity: O(n).** Every node is visited once.
- **Space complexity: O(h).** Recursion stack depth equals the height of the tree; O(log n) balanced, O(n) worst case (skewed tree).

```java
package com.lc;

public class LC_0104_MaxDepthBinaryTree {
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
  }
}
```

**Key insight:** The depth of any node is simply one more than the deeper of its two subtrees, making this a natural post-order computation expressible in a single line.
