---
description: MEDIUM
---

# 129. Sum Root to Leaf Numbers

You are given the `root` of a binary tree containing digits from `0` to `9` only.

Each root-to-leaf path in the tree represents a number. For example, the root-to-leaf path `1 -> 2 -> 3` represents the number `123`.

Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A **leaf** node is a node with no children.

**Example 1:**

```text
Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
```

**Example 2:**

```text
Input: root = [4,9,0,5,1]
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
```

**Constraints:**

- The number of nodes in the tree is in the range `[1, 1000]`.
- `0 <= Node.val <= 9`
- The depth of the tree will not exceed `10`.

---

## Approach: DFS with Running Sum

Perform a DFS traversal, carrying the current number formed so far by multiplying the accumulated value by 10 and adding the current node's digit. When a leaf is reached, add the formed number to a running total.

#### Complexity Analysis

- **Time complexity: O(n).** Every node is visited exactly once.
- **Space complexity: O(h).** The recursion stack depth equals the height `h` of the tree (O(n) in the worst case for a skewed tree).

```java
package com.lc;

public class LC_0129_RootToLeafSum {
  public int sumNumbers(TreeNode root) {
    int[] sum = new int[1];
    helper(root, sum, 0);
    return sum[0];
  }

  private void helper(TreeNode root, int[] sum, int current) {
    if (root == null) {
      return;
    }
    current = current * 10 + root.val;
    if (root.left == null && root.right == null) {
      sum[0] += current;
    }
    helper(root.left, sum, current);
    helper(root.right, sum, current);
  }
}
```

**Key insight:** Multiplying the accumulated value by 10 before adding the next digit reconstructs the decimal number digit-by-digit as the recursion descends, avoiding any string conversion.
