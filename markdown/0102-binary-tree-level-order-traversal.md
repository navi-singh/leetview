---
description: MEDIUM
---

# 102. Binary Tree Level Order Traversal

Given the root of a binary tree, return the level order traversal of its nodes' values (i.e., from left to right, level by level).

**Example 1:**

```text
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
```

**Example 2:**

```text
Input: root = [1]
Output: [[1]]
```

**Example 3:**

```text
Input: root = []
Output: []
```

**Constraints:**

- The number of nodes in the tree is in the range `[0, 2000]`.
- `-1000 <= Node.val <= 1000`

---

## Approach: BFS with Level Size Tracking

Use a queue for BFS traversal. Track the number of nodes remaining in the current level (`levelSize`). When `levelSize` reaches zero, the current level is complete — add it to the result and reset `levelSize` to the current queue size (which holds the next level's nodes).

#### Complexity Analysis

- **Time complexity: O(n).** Each node is enqueued and dequeued exactly once.
- **Space complexity: O(n).** The queue holds at most all nodes at the widest level, which can be O(n) for a complete binary tree.

```java
package com.lc;

import java.util.*;
import java.util.ArrayList;

class LC102_LevelOrderTraversal {
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    int levelSize = 1;
    List<Integer> tempList = new ArrayList<Integer>();
    queue.add(root);
    while (queue.size() > 0) {
      TreeNode elem = queue.remove();
      tempList.add(elem.val);
      levelSize--;
      if (elem.left != null) {
        queue.add(elem.left);
      }
      if (elem.right != null) {
        queue.add(elem.right);
      }
      if (levelSize == 0) {
        res.add(new ArrayList(tempList));
        tempList.clear();
        levelSize = queue.size();
      }
    }
    return res;
  }
}
```

**Key insight:** Snapshotting `queue.size()` after each level drains naturally separates levels without needing a sentinel node or two-queue technique.
