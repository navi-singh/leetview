---
description: MEDIUM
---

# 173. Binary Search Tree Iterator

Implement the `BSTIterator` class that represents an iterator over the **in-order traversal** of a binary search tree (BST):

- `BSTIterator(TreeNode root)` Initializes an object of the `BSTIterator` class. The `root` of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
- `boolean hasNext()` Returns `true` if there exists a number in the traversal to the right of the pointer, otherwise returns `false`.
- `int next()` Moves the pointer to the right, then returns the number at the pointer.

Notice that by initializing the pointer to a non-existent smallest number, the first call to `next()` will return the smallest element in the BST.

You may assume that `next()` calls will always be valid. That is, there will be at least a next number in the in-order traversal when `next()` is called.

**Example 1:**

```text
Input:
["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
Output:
[null, 3, 7, true, 9, true, 15, true, 20, false]

Explanation:
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next();    // return 3
bSTIterator.next();    // return 7
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 9
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 15
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 20
bSTIterator.hasNext(); // return False
```

**Constraints:**

- The number of nodes in the tree is in the range `[1, 10^5]`.
- `0 <= Node.val <= 10^6`
- At most `10^5` calls will be made to `hasNext` and `next`.

**Follow-up:**

- Could you implement `next()` and `hasNext()` to run in average `O(1)` time and use `O(h)` memory, where `h` is the height of the tree?

---

## Approach: Controlled In-Order Traversal with a Stack

During construction, push all left-spine nodes onto a stack (the leftmost node is on top, representing the smallest element). On each `next()` call, pop the top node, then if it has a right child, push the right child and all of its left-spine descendants onto the stack. `hasNext()` simply checks whether the stack is non-empty.

#### Complexity Analysis

- **Time complexity: O(1) amortized per `next()` call.** Each node is pushed and popped exactly once across all calls, so `n` calls cost O(n) total.
- **Space complexity: O(h).** Where `h` is the height of the tree — the stack holds at most one path from root to a leaf at any time.

```java
package com.lc;

import java.util.Stack;

public class LC_0173_BSTIterator {
  class BSTIterator {
    Stack<TreeNode> st;

    public BSTIterator(TreeNode root) {
      st = new Stack<TreeNode>();
      while (root != null) {
        st.push(root);
        root = root.left;
      }
    }

    public int next() {
      TreeNode node = st.pop();
      int res = node.val;
      if (node.right != null) {
        node = node.right;
        while (node != null) {
          st.push(node);
          node = node.left;
        }
      }
      return res;
    }

    public boolean hasNext() {
      return !st.empty();
    }
  }
}
```

**Key insight:** Lazily pushing only the left spine on demand mimics the call stack of a recursive in-order traversal while bounding memory to O(h) instead of O(n).
