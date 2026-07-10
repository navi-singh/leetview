---
description: EASY
---

# 101. Symmetric Tree

Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

**Example 1:**

```text
Input: root = [1,2,2,3,4,4,3]
Output: true
```

**Example 2:**

```text
Input: root = [1,2,2,null,3,null,3]
Output: false
```

**Constraints:**

- The number of nodes in the tree is in the range `[1, 1000]`.
- `-100 <= Node.val <= 100`

**Follow up:** Could you solve it both recursively and iteratively?

---

## Approach: Recursive Mirror Check

Decompose the problem into checking whether the left and right subtrees of the root are mirrors of each other. Two subtrees are mirrors if their roots have equal values and the outer children match while the inner children match.

#### Complexity Analysis

- **Time complexity: O(n).** Each node is visited once during the symmetric comparison.
- **Space complexity: O(h).** Recursion stack depth is bounded by the height of the tree.

```java
package com.lc;

public class LC_0101_SymmetricTree {
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isSymmetric(root.left, root.right);
  }

  private boolean isSymmetric(TreeNode left, TreeNode right) {
    if (left == null && right == null) {
      return true;
    }
    if (left == null || right == null) {
      return false;
    }
    return (left.val == right.val)
        && isSymmetric(left.left, right.right)
        && isSymmetric(left.right, right.left);
  }
}
```

**Key insight:** Symmetry means the outer pair (`left.left` vs `right.right`) and the inner pair (`left.right` vs `right.left`) must both be mirrors — crossing the pointers captures this naturally.
