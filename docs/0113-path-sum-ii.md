---
description: MEDIUM
---

# 113. Path Sum II

Given the root of a binary tree and an integer `targetSum`, return all root-to-leaf paths where the sum of the node values in the path equals `targetSum`. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

**Example 1:**

```text
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
```

**Example 2:**

```text
Input: root = [1,2,3], targetSum = 5
Output: []
```

**Example 3:**

```text
Input: root = [1,2], targetSum = 0
Output: []
```

**Constraints:**

- The number of nodes in the tree is in the range `[0, 5000]`.
- `-1000 <= Node.val <= 1000`
- `-1000 <= targetSum <= 1000`

---

## Approach: Backtracking DFS

Perform a DFS while maintaining a running sum and a temporary path list. When a leaf is reached and the running sum equals the target, add a snapshot of the current path to the results. Backtrack by removing the last element from the path after returning from each recursive call.

#### Complexity Analysis

- **Time complexity: O(n^2).** Each node is visited once, and copying a path to the result takes O(n) time in the worst case.
- **Space complexity: O(n).** The recursion stack and temporary path both grow up to O(n) for a skewed tree.

```java
package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0113_PathSum2 {
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> res = new ArrayList();
    List<Integer> temp = new ArrayList<Integer>();
    pathSumHelper(root, sum, 0, temp, res);
    return res;
  }

  private void pathSumHelper(
      TreeNode root, int sum, int current, List<Integer> temp, List<List<Integer>> res) {
    if (root == null) {
      return;
    }
    current += root.val;
    temp.add(root.val);
    if (root.left == null && root.right == null) {
      if (sum == current) {
        res.add(new ArrayList(temp));
      }
    }
    pathSumHelper(root.left, sum, current, temp, res);
    pathSumHelper(root.right, sum, current, temp, res);
    temp.remove(temp.size() - 1);
  }
}
```

**Key insight:** Removing the last element from `temp` after both recursive calls (backtracking) allows the single list to represent every root-to-leaf path explored without allocating a new list at each level.
