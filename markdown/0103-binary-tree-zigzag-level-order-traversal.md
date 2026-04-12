---
description: MEDIUM
---

# 103. Binary Tree Zigzag Level Order Traversal

Given the root of a binary tree, return the zigzag level order traversal of its nodes' values (i.e., from left to right, then right to left for the next level, and so on).

**Example 1:**

```text
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
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
- `-100 <= Node.val <= 100`

---

## Approach: BFS with Conditional Reversal

Perform standard BFS level-order traversal and track the current level index. After collecting all nodes for a level, reverse the list before adding it to the result if the level index is odd (right-to-left levels).

#### Complexity Analysis

- **Time complexity: O(n).** All nodes are visited once; reversal per level costs O(w) where w is the level width, summing to O(n) overall.
- **Space complexity: O(n).** The queue and temporary list together hold at most O(n) elements.

```java
package com.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LC_0103_ZigZagTraversal {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    List<Integer> tempList = new ArrayList<Integer>();
    int levelSize = 1;
    queue.add(root);
    while (queue.size() > 0) {
      TreeNode node = queue.pop();
      tempList.add(node.val);
      levelSize--;
      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
      if (levelSize == 0) {
        if (res.size() % 2 == 1) {
          Collections.reverse(tempList);
        }
        res.add(new ArrayList<Integer>(tempList));
        tempList.clear();
        levelSize = queue.size();
      }
    }
    return res;
  }
}
```

**Key insight:** Using `res.size() % 2 == 1` to detect odd-indexed levels and reversing in-place is simpler than maintaining separate deques for alternating directions.
