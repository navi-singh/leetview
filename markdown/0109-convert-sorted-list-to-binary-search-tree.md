---
description: MEDIUM
---

# 109. Convert Sorted List to Binary Search Tree

Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height-balanced binary search tree.

**Example 1:**

```text
Input: head = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
```

**Example 2:**

```text
Input: head = []
Output: []
```

**Constraints:**

- The number of nodes in the linked list is in the range `[0, 2 * 10^4]`.
- `-10^5 <= Node.val <= 10^5`

---

## Approach: In-Order Simulation with Global Pointer

Rather than finding the midpoint of the list each time (which would be O(n log n)), simulate an in-order traversal of the BST using index boundaries. The recursion builds the left subtree first, then reads the next value from the linked list (advancing a global pointer `h`) to set the current node's value, then builds the right subtree — exactly mirroring an in-order traversal.

#### Complexity Analysis

- **Time complexity: O(n).** Each node in the list is read exactly once by the advancing global pointer.
- **Space complexity: O(log n).** The recursion depth is O(log n) for the balanced tree; O(n) space is used for the output nodes.

```java
package com.lc;

public class LC_0109_ListToBST {
  static ListNode h;

  public TreeNode sortedListToBST(ListNode head) {
    if (head == null) {
      return null;
    }
    this.h = head;
    ListNode temp = head;
    int size = 0;
    while (temp != null) {
      temp = temp.next;
      size++;
    }
    return helper(0, size - 1);
  }

  private TreeNode helper(int left, int right) {
    if (left > right) {
      return null;
    }
    int mid = left + (right - left) / 2;
    TreeNode leftNode = helper(left, mid - 1);
    TreeNode node = new TreeNode(h.val);

    h = h.next;
    TreeNode rightNode = helper(mid + 1, right);
    node.left = leftNode;
    node.right = rightNode;
    return node;
  }
}
```

**Key insight:** Building the left subtree recursively before reading from the list guarantees that `h` naturally points to the correct "middle" element when the current node is created, because in-order traversal visits left before root before right.
