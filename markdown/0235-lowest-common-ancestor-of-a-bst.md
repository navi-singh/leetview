---
description: MEDIUM
---

# 235. Lowest Common Ancestor of a Binary Search Tree

Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the [definition of LCA on Wikipedia](https://en.wikipedia.org/wiki/Lowest_common_ancestor): "The lowest common ancestor is defined between two nodes `p` and `q` as the lowest node in `T` that has both `p` and `q` as descendants (where we allow **a node to be a descendant of itself**)."

**Example 1:**

```text
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
```

**Example 2:**

```text
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
```

**Example 3:**

```text
Input: root = [2,1], p = 2, q = 1
Output: 2
```

**Constraints:**

- The number of nodes in the tree is in the range `[2, 10^5]`.
- `-10^9 <= Node.val <= 10^9`
- All `Node.val` are **unique**.
- `p != q`
- `p` and `q` will exist in the BST.

---

## Approach: Iterative BST Property Navigation

Leverage the BST ordering property: if both `p` and `q` are smaller than the current node, the LCA must be in the left subtree; if both are larger, in the right subtree. The first node that lies between `p` and `q` (inclusive) is the LCA.

#### Complexity Analysis

- **Time complexity: O(h).** Where `h` is the height of the BST; O(log n) for balanced trees, O(n) worst case.
- **Space complexity: O(1).** Iterative traversal with no extra space.

```java
package com.lc;

public class LC_0235_LCABST {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while (root != null) {
      if (root.val > p.val && root.val > q.val) {
        root = root.left;
      } else if (root.val < p.val && root.val < q.val) {
        root = root.right;
      } else {
        return root;
      }
    }
    return null;
  }
}
```

**Key insight:** The BST property guarantees that the first node whose value is between `p.val` and `q.val` (or equals one of them) is the split point — and therefore the LCA — eliminating the need for any backtracking or parent pointers.
