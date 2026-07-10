# [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the <a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor" target="_blank">definition of LCA on Wikipedia</a>: “The lowest common ancestor is defined between two nodes `p` and `q` as the lowest node in `T` that has both `p` and `q` as descendants (where we allow <b>a node to be a descendant of itself</b>).”

**Example 1:** 
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" style="width: 200px; height: 190px;">

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```

**Example 2:** 
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" style="width: 200px; height: 190px;">

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
```

**Example 3:** 

```
Input: root = [1,2], p = 1, q = 2
Output: 1
```

**Constraints:** 

- The number of nodes in the tree is in the range `[2, 10^5]`.
- `-10^9 <= Node.val <= 10^9`
- All `Node.val` are **unique** .
- `p != q`
- `p` and `q` will exist in the tree.

### Solution
```java
package com.lc;

public class LC_0236_LCABinaryTree {
  TreeNode res = null;

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    helper(root, p, q);
    return this.res;
  }

  public int helper(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return 0;
    }

    int left = helper(root.left, p, q);
    int right = helper(root.right, p, q);
    int cur = (root == p || root == q) ? 1 : 0;
    int totalFind = left + right + cur;
    if (totalFind >= 2 && res == null) {
      this.res = root;
    }
    return totalFind;
  }
}
```
