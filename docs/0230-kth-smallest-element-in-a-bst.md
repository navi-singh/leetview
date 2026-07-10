---
description: MEDIUM
---

# 230. Kth Smallest Element in a BST

Given the `root` of a binary search tree, and an integer `k`, return the `k`th smallest value (**1-indexed**) of all the values of the nodes in the tree.

**Example 1:**

```text
Input: root = [3,1,4,null,2], k = 1
Output: 1
```

**Example 2:**

```text
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
```

**Constraints:**

- The number of nodes in the tree is `n`.
- `1 <= k <= n <= 10^4`
- `0 <= Node.val <= 10^4`

---

## Approach: Iterative In-order Traversal

Perform an iterative in-order traversal (left → root → right) using an explicit stack. BST in-order traversal visits nodes in ascending order, so the k-th node popped is the k-th smallest element.

#### Complexity Analysis

- **Time complexity: O(H + k).** H is the height of the tree; we descend to the leftmost node in O(H) and then visit k nodes.
- **Space complexity: O(H).** The explicit stack holds at most H nodes at any time.

```java
package com.lc;

import java.util.LinkedList;

public class LC_0230_KthSmallestBST {
  public int kthSmallest(TreeNode root, int k) {
    if (root == null) {
      return -1;
    }
    LinkedList<TreeNode> st = new LinkedList();
    while (true) {
      while (root != null) {
        st.add(root);
        root = root.left;
      }
      root = st.removeLast();
      if (--k == 0) {
        return root.val;
      }
      root = root.right;
    }
  }
}
```

**Key insight:** In-order traversal of a BST produces elements in sorted ascending order, so simply counting to the k-th visited node yields the k-th smallest value without sorting or extra storage.
