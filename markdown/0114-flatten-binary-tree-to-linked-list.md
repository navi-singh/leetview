---
description: MEDIUM
---

# 114. Flatten Binary Tree to Linked List

Given the root of a binary tree, flatten the tree into a "linked list":

- The "linked list" should use the same `TreeNode` class where the `right` child pointer points to the next node in the list and the `left` child pointer is always `null`.
- The "linked list" should be in the same order as a pre-order traversal of the binary tree.

**Example 1:**

```text
Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
```

**Example 2:**

```text
Input: root = []
Output: []
```

**Example 3:**

```text
Input: root = [0]
Output: [0]
```

**Constraints:**

- The number of nodes in the tree is in the range `[0, 2000]`.
- `-100 <= Node.val <= 100`

**Follow up:** Can you flatten the tree in-place (with `O(1)` extra space)?

---

## Approach: Post-Order Recursive Rewiring

Process the tree bottom-up (post-order). Each call returns the tail node of the flattened subtree. If the current node has a left subtree, insert the flattened left subtree between the root and the right subtree by: connecting the left tail to the original right child, moving the left subtree to the right pointer, and nulling the left pointer.

#### Complexity Analysis

- **Time complexity: O(n).** Every node is visited exactly once.
- **Space complexity: O(h).** The recursion stack depth equals the tree height.

```java
package com.lc;

public class LC_0114_FlatterTreeToLinkedList {
  public void flatten(TreeNode root) {
    process(root);
  }

  public TreeNode process(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode lTail = process(root.left);
    TreeNode rTail = process(root.right);
    if (root.left != null) {
      TreeNode temp = root.right;
      root.right = root.left;
      lTail.right = temp;
      root.left = null;
    }
    if (rTail != null) {
      return rTail;
    }
    if (lTail != null) {
      return lTail;
    }
    return root;
  }
}
```

**Key insight:** Returning the tail of each flattened subtree avoids traversing to the end of the left list to stitch it to the right — each call passes the tail up for the parent to use directly.
