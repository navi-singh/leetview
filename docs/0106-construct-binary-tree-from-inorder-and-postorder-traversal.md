---
description: MEDIUM
---

# 106. Construct Binary Tree from Inorder and Postorder Traversal

Given two integer arrays `inorder` and `postorder` where `inorder` is the inorder traversal of a binary tree and `postorder` is the postorder traversal of the same tree, construct and return the binary tree.

**Example 1:**

```text
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
```

**Example 2:**

```text
Input: inorder = [-1], postorder = [-1]
Output: [-1]
```

**Constraints:**

- `1 <= inorder.length <= 3000`
- `postorder.length == inorder.length`
- `-3000 <= inorder[i], postorder[i] <= 3000`
- `inorder` and `postorder` consist of unique values.
- Each value of `postorder` also appears in `inorder`.
- `inorder` is guaranteed to be the inorder traversal of the tree.
- `postorder` is guaranteed to be the postorder traversal of the tree.

---

## Approach: Recursive Divide and Conquer

The last element of the postorder array is always the root. Find this root in the inorder array to split left and right subtrees. Recursively build both subtrees using the appropriate index ranges in both arrays.

#### Complexity Analysis

- **Time complexity: O(n^2).** Linear scan to find the root in inorder at each recursive call; a HashMap would reduce this to O(n).
- **Space complexity: O(n).** Recursion stack depth is O(n) in the worst case plus O(n) for the output tree.

```java
package com.lc;

public class LC_0106_ConstructTreePostAndInorder {
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    int inStart = 0;
    int inEnd = inorder.length - 1;
    int postStart = 0;
    int postEnd = postorder.length - 1;

    return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
  }

  public TreeNode buildTree(
      int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
    if (inStart > inEnd || postStart > postEnd) return null;

    int rootValue = postorder[postEnd];
    TreeNode root = new TreeNode(rootValue);

    int inIndex = 0;
    for (int i = 0; i < inorder.length; i++) {
      if (inorder[i] == rootValue) {
        inIndex = i;
        break;
      }
    }

    root.left =
        buildTree(
            inorder,
            inStart,
            inIndex - 1,
            postorder,
            postStart,
            postStart + inIndex - (inStart + 1));
    root.right =
        buildTree(
            inorder, inIndex + 1, inEnd, postorder, postStart + inIndex - inStart, postEnd - 1);
    return root;
  }
}
```

**Key insight:** Just as with preorder+inorder, the left subtree size (`inIndex - inStart`) is consistent across both arrays and drives the precise partitioning of the postorder range.
