---
description: MEDIUM
---

# 116. Populating Next Right Pointers in Each Node

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to `NULL`.

Initially, all next pointers are set to `NULL`.

**Example 1:**

```text
Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: The # symbol denotes the end of each level.
```

**Example 2:**

```text
Input: root = []
Output: []
```

**Constraints:**

- The number of nodes in the tree is in the range `[0, 2^12 - 1]`.
- `-1000 <= node.val <= 1000`

**Follow up:**
- You may only use constant extra space.
- The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.

---

## Approach: Level-by-Level Pointer Traversal Using Already-Set Next Pointers

Use the previously set `next` pointers on each level to traverse the current level and wire up the next level's pointers. Starting from the leftmost node of each level, walk rightward using `next` and connect children. Then recurse into the leftmost child of the next level.

#### Complexity Analysis

- **Time complexity: O(n).** Each node is visited exactly once.
- **Space complexity: O(log n).** Recursion depth equals the height of the perfect binary tree.

```java
package com.lc;

public class LC_0116_PopulateNextPointer {
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

**Key insight:** By the time you process level `k+1`, all `next` pointers on level `k` are already set, so you can walk level `k` horizontally for free to correctly wire up the children.
