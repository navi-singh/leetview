---
description: EASY
---

# 144. Binary Tree Preorder Traversal

Given the `root` of a binary tree, return the preorder traversal of its nodes' values.

**Example 1:**

```text
Input: root = [1,null,2,3]
Output: [1,2,3]
```

**Example 2:**

```text
Input: root = []
Output: []
```

**Example 3:**

```text
Input: root = [1]
Output: [1]
```

**Constraints:**

- The number of nodes in the tree is in the range `[0, 100]`.
- `-100 <= Node.val <= 100`

---

## Approach: Recursive DFS (Root → Left → Right)

Recursively visit the current node first (add to result), then recurse on the left subtree, then the right subtree — following the classic preorder: root, left, right.

#### Complexity Analysis

- **Time complexity: O(n).** Every node is visited exactly once.
- **Space complexity: O(h).** The recursion stack depth equals the tree height `h` (O(n) worst case for a skewed tree).

```java
package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0144_PreorderTraversal {
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    preorderTraversal(root, res);
    return res;
  }

  private void preorderTraversal(TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }
    res.add(root.val);
    preorderTraversal(root.left, res);
    preorderTraversal(root.right, res);
  }
}
```

**Key insight:** Adding the node's value before recursing on children is the defining characteristic of preorder traversal; swapping this single line's position relative to the recursive calls converts it to inorder or postorder.
