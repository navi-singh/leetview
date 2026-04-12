---
description: EASY
---

# 100. Same Tree

Given the roots of two binary trees `p` and `q`, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

**Example 1:**

```text
Input: p = [1,2,3], q = [1,2,3]
Output: true
```

**Example 2:**

```text
Input: p = [1,2], q = [1,null,2]
Output: false
```

**Example 3:**

```text
Input: p = [1,2,1], q = [1,1,2]
Output: false
```

**Constraints:**

- The number of nodes in both trees is in the range `[0, 100]`.
- `-10^4 <= Node.val <= 10^4`

---

## Approach: Recursive DFS

Check both trees simultaneously by recursing into corresponding left and right subtrees. At each step, verify both nodes are null (equal), one is null (unequal), or their values match before proceeding deeper.

#### Complexity Analysis

- **Time complexity: O(n).** Every node in both trees is visited exactly once.
- **Space complexity: O(h).** The call stack depth equals the height of the tree; O(log n) for balanced, O(n) worst case.

```java
package com.lc;

public class LC_0100_SameTree {
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }
    if (p == null || q == null) {
      return false;
    }
    return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }
}
```

**Key insight:** The three base cases (both null, one null, values differ) cover all mismatch scenarios, so the recursion only needs to confirm structural and value equality simultaneously.
