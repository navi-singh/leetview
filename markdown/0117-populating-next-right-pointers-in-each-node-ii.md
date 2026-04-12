---
description: MEDIUM
---

# 117. Populating Next Right Pointers in Each Node II

Given a binary tree (not necessarily perfect), populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to `NULL`.

Initially, all next pointers are set to `NULL`.

**Example 1:**

```text
Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
```

**Example 2:**

```text
Input: root = []
Output: []
```

**Constraints:**

- The number of nodes in the tree is in the range `[0, 6000]`.
- `-100 <= node.val <= 100`

**Follow up:** Can you solve the problem using only `O(1)` extra memory?

---

## Approach: Level-by-Level Traversal Using Next Pointers (Handles Sparse Nodes)

Extend the approach from problem 116 to handle imperfect binary trees. Before processing each level, skip any parent nodes that have no children (moving via `next`). Then walk the current level using the already-set `next` pointers, wiring up whichever children exist (left, right, or both) for the next level.

#### Complexity Analysis

- **Time complexity: O(n).** Each node is visited exactly once.
- **Space complexity: O(h).** Recursion depth is the height of the tree; O(log n) for balanced, O(n) worst case.

```java
package com.lc;

public class LC_0117_PopulateNextPointer2 {
  class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }
  }
  ;

  public Node connect(Node root) {
    Node res = root;
    helper(root);
    return res;
  }

  private void helper(Node root) {
    while (root != null && (root.left == null && root.right == null)) {
      root = root.next;
    }
    if (root == null) {
      return;
    }
    Node left = root.left != null ? root.left : root.right;
    Node cur = left;
    while (root != null) {
      if (cur == root.left) {
        if (root.right != null) {
          cur.next = root.right;
          cur = cur.next;
        }
        root = root.next;
      } else if (cur == root.right) {
        root = root.next;
      } else {
        if (root.left == null && root.right == null) {
          root = root.next;
        } else {
          cur.next = root.left != null ? root.left : root.right;
          cur = cur.next;
        }
      }
    }
    helper(left);
  }
}
```

**Key insight:** Skipping leaf nodes at the start of each helper call (the initial `while` loop) ensures that `root` always points to the first parent with at least one child, preventing null pointer issues when the tree is sparse.
