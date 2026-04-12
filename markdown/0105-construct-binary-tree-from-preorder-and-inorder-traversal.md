---
description: MEDIUM
---

# 105. Construct Binary Tree from Preorder and Inorder Traversal

Given two integer arrays `preorder` and `inorder` where `preorder` is the preorder traversal of a binary tree and `inorder` is the inorder traversal of the same tree, construct and return the binary tree.

**Example 1:**

```text
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
```

**Example 2:**

```text
Input: preorder = [-1], inorder = [-1]
Output: [-1]
```

**Constraints:**

- `1 <= preorder.length <= 3000`
- `inorder.length == preorder.length`
- `-3000 <= preorder[i], inorder[i] <= 3000`
- `preorder` and `inorder` consist of unique values.
- Each value of `inorder` also appears in `preorder`.
- `preorder` is guaranteed to be the preorder traversal of the tree.
- `inorder` is guaranteed to be the inorder traversal of the tree.

---

## Approach: Recursive Divide and Conquer

The first element of the preorder array is always the root. Find this root value in the inorder array to determine the boundary between left and right subtrees. Recursively build the left subtree from elements before the root in inorder, and the right subtree from elements after it.

#### Complexity Analysis

- **Time complexity: O(n^2).** Finding the root index in the inorder array takes O(n) per call, and there are n recursive calls. Using a HashMap for O(1) lookup would reduce this to O(n).
- **Space complexity: O(n).** The recursion stack and the resulting tree nodes each take O(n) space.

```java
package com.lc;

public class LC_0105_ConstructTree {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    int inStart = 0, inEnd = inorder.length - 1;
    int preStart = 0, preEnd = preorder.length - 1;
    return helper(preorder, preStart, preEnd, inorder, inStart, inEnd);
  }

  private TreeNode helper(
      int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
    if (inStart > inEnd || preStart > preEnd) {
      return null;
    }
    int val = preorder[preStart];
    TreeNode node = new TreeNode(val);
    int inIndex = 0;
    for (int i = 0; i < inorder.length; i++) {
      if (val == inorder[i]) {
        inIndex = i;
        break;
      }
    }
    node.left =
        helper(
            preorder, preStart + 1, preStart + (inIndex - inStart), inorder, inStart, inIndex - 1);
    node.right =
        helper(preorder, preStart + (inIndex - inStart) + 1, preEnd, inorder, inIndex + 1, inEnd);
    return node;
  }
}
```

**Key insight:** The number of nodes in the left subtree (`inIndex - inStart`) is the same whether measured in the inorder or preorder array, which lets you precisely slice both arrays for each recursive call.
