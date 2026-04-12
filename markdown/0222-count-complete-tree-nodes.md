---
description: MEDIUM
---

# 222. Count Complete Tree Nodes

Given the `root` of a **complete** binary tree, return the number of the nodes in the tree.

According to [Wikipedia](http://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees), every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between `1` and `2^h` nodes inclusive at the last level `h`.

Design an algorithm that runs in less than `O(n)` time complexity.

**Example 1:**

```text
Input: root = [1,2,3,4,5,6]
Output: 6
```

**Example 2:**

```text
Input: root = []
Output: 0
```

**Example 3:**

```text
Input: root = [1]
Output: 1
```

**Constraints:**

- The number of nodes in the tree is in the range `[0, 5 * 10^4]`.
- `0 <= Node.val <= 5 * 10^4`
- The tree is guaranteed to be **complete**.

---

## Approach: Binary Search on Tree Height

Compare the left-subtree height (traversing only left children) and the right-subtree height (traversing only right children) at each node. If they are equal, the left subtree is a perfect binary tree and its node count can be computed in O(1) using `2^h - 1`. If they differ, recurse into both children and add 1 for the current node. This exploits the complete-tree property to avoid visiting every node.

#### Complexity Analysis

- **Time complexity: O(log^2 n).** At each level we spend O(log n) to compute heights, and there are O(log n) levels of recursion.
- **Space complexity: O(log n).** The recursion stack depth equals the height of the tree.

```java
package com.lc;

public class LC_0222_CountCompleteTreeNodes {
  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = getLeftHeight(root) + 1;
    int right = getRightHeight(root) + 1;
    if (left == right) {
      return (2 << (left - 1)) - 1;
    } else {
      return countNodes(root.left) + countNodes(root.right) + 1;
    }
  }

  private int getLeftHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int height = 0;
    while (root.left != null) {
      root = root.left;
      height++;
    }
    return height;
  }

  private int getRightHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int height = 0;
    while (root.right != null) {
      root = root.right;
      height++;
    }
    return height;
  }
}
```

**Key insight:** In a complete binary tree, if the leftmost depth equals the rightmost depth, the subtree is a perfect binary tree whose size is `2^h - 1`, computable in O(1). This lets us skip entire subtrees and achieve sub-linear time.
