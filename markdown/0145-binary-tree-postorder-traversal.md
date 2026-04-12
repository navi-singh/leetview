---
description: EASY
---

# 145. Binary Tree Postorder Traversal

Given the `root` of a binary tree, return the postorder traversal of its nodes' values.

**Example 1:**

```text
Input: root = [1,null,2,3]
Output: [3,2,1]
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

## Approach: Recursive DFS (Left → Right → Root)

Recursively visit the left subtree, then the right subtree, then add the current node's value — following the classic postorder: left, right, root.

#### Complexity Analysis

- **Time complexity: O(n).** Every node is visited exactly once.
- **Space complexity: O(h).** The recursion stack depth equals the tree height `h` (O(n) worst case for a skewed tree).

```java
package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0145_PostOrderTraversal {
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    postorderTraversal(root, res);
    return res;
  }

  private void postorderTraversal(TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }
    postorderTraversal(root.left, res);
    postorderTraversal(root.right, res);
    res.add(root.val);
  }
}
```

**Key insight:** Adding the node's value after both children have been fully processed (left then right) ensures children are always emitted before their parent, which is the defining property of postorder traversal.
