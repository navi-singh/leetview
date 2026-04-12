---
description: MEDIUM
---

# 95. Unique Binary Search Trees II

Given an integer `n`, return all structurally unique BSTs (binary search trees), which has exactly `n` nodes of unique values from `1` to `n`. Return the answer in any order.

**Example 1:**

```text
Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
```

**Example 2:**

```text
Input: n = 1
Output: [[1]]
```

**Constraints:**

- `1 <= n <= 8`

---

## Approach 1: Recursive Enumeration

A helper method `helper(m, n)` returns all unique BSTs that can be built using the values from `m` to `n` inclusive. For each value `i` in `[m, n]`, `i` is chosen as the root, then all unique left subtrees are generated from `[m, i-1]` and all unique right subtrees from `[i+1, n]`. Every combination of a left and right subtree is paired with root `i` to produce a new tree, which is added to the result list. The base case (when `m > n`) returns a list containing a single `null` node, representing an empty subtree.

#### Complexity Analysis

- **Time complexity: O(n * C(n))** where C(n) is the n-th Catalan number. The number of unique BSTs grows as the Catalan number sequence.
- **Space complexity: O(n * C(n)).** All generated trees are stored in the result.

```java
public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
        return new ArrayList<TreeNode>();
    }
    return helper(1, n);
}

private List<TreeNode> helper(int m, int n) {
    List<TreeNode> res = new ArrayList<TreeNode>();
    if (m > n) {
        res.add(null);
        return res;
    }
    for (int i = m; i <= n; i++) {
        List<TreeNode> left = helper(m, i - 1);
        List<TreeNode> right = helper(i + 1, n);
        for (TreeNode l : left) {
            for (TreeNode r : right) {
                TreeNode curr = new TreeNode(i);
                curr.left = l;
                curr.right = r;
                res.add(curr);
            }
        }
    }
    return res;
}
```

**Key insight:** Splitting the value range at each candidate root naturally enforces the BST property (all left subtree values are smaller, all right subtree values are larger), so every combination produced by the recursive calls is guaranteed to be a valid BST.
