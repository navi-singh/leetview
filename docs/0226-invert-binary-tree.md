---
description: EASY
---

# 226. Invert Binary Tree

Given the `root` of a binary tree, invert the tree, and return its root.

**Example 1:**

```text
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
```

**Example 2:**

```text
Input: root = [2,1,3]
Output: [2,3,1]
```

**Example 3:**

```text
Input: root = []
Output: []
```

**Constraints:**

- The number of nodes in the tree is in the range `[0, 100]`.
- `-100 <= Node.val <= 100`

---

## Approach: Recursive Pre-order Swap

Swap the left and right children of the current node, then recursively invert each subtree. The base case is a `null` node.

#### Complexity Analysis

- **Time complexity: O(n).** Every node is visited exactly once.
- **Space complexity: O(h).** The recursion stack depth is proportional to the height `h` of the tree; O(log n) for balanced trees and O(n) in the worst case.

```java
package com.lc;

public class LC_0226_InvertBinaryTree {
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return root;
    }
    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;
    invertTree(root.left);
    invertTree(root.right);
    return root;
  }
}
```

**Key insight:** Swapping children before recursing (pre-order) keeps the logic simple — once the children are swapped at the current node, the recursive calls handle the rest of the tree automatically.
