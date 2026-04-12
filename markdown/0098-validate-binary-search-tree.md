---
description: MEDIUM
---

# 98. Validate Binary Search Tree

Given the `root` of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

- The left subtree of a node contains only nodes with keys **less than** the node's key.
- The right subtree of a node contains only nodes with keys **greater than** the node's key.
- Both the left and right subtrees must also be binary search trees.

**Example 1:**

```text
Input: root = [2,1,3]
Output: true
```

**Example 2:**

```text
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
```

**Constraints:**

- The number of nodes in the tree is in the range `[1, 10^4]`
- `-2^31 <= Node.val <= 2^31 - 1`

---

## Approach 1: Recursive Range Validation

Each node is validated against a `(min, max)` range that tightens as we recurse down the tree. Initially the range is `(-∞, +∞)`. When we recurse into the left subtree of node `v`, the upper bound becomes `v.val`. When we recurse into the right subtree, the lower bound becomes `v.val`. A node fails validation if its value does not lie strictly within `(min, max)`. Using `double` for the bounds avoids integer overflow edge cases when node values are `Integer.MIN_VALUE` or `Integer.MAX_VALUE`.

#### Complexity Analysis

- **Time complexity: O(n).** Every node is visited exactly once.
- **Space complexity: O(h).** The recursion stack depth equals the tree height `h`; worst case O(n) for a skewed tree.

```java
public boolean isValidBST(TreeNode root) {
    if (root == null) {
        return true;
    }
    return helper(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
}

private boolean helper(TreeNode root, double min, double max) {
    if (root == null) {
        return true;
    }
    if (!(root.val > min && root.val < max)) {
        return false;
    }
    return helper(root.left, min, root.val) && helper(root.right, root.val, max);
}
```

**Key insight:** Passing explicit min/max bounds down the recursion tree correctly propagates ancestor constraints — a common mistake is only checking parent-child relationships, which misses cases like a right-subtree node being smaller than a grandparent.
