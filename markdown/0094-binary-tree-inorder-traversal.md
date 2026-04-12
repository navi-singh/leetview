---
description: EASY
---

# 94. Binary Tree Inorder Traversal

Given the `root` of a binary tree, return the inorder traversal of its nodes' values.

**Example 1:**

```text
Input: root = [1,null,2,3]
Output: [1,3,2]
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

- The number of nodes in the tree is in the range `[0, 100]`
- `-100 <= Node.val <= 100`

---

## Approach 1: Iterative Stack-Based Inorder

An explicit stack simulates the call stack of the recursive approach. While the current node is non-null, it is pushed onto the stack and we descend left. When `root` becomes null we pop from the stack, record the node's value, and then turn right. This continues until both the current pointer and the stack are empty.

#### Complexity Analysis

- **Time complexity: O(n).** Every node is pushed and popped exactly once.
- **Space complexity: O(n).** The stack holds at most `h` nodes where `h` is the tree height; worst case O(n) for a skewed tree.

```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    if (root == null) {
        return res;
    }
    Stack<TreeNode> st = new Stack<TreeNode>();
    while (root != null || !st.empty()) {
        if (root != null) {
            st.add(root);
            root = root.left;
        } else {
            root = st.pop();
            res.add(root.val);
            root = root.right;
        }
    }
    return res;
}
```

**Key insight:** The iterative approach mirrors the recursive one exactly: push nodes while going left, then visit and go right — the stack replaces the implicit recursion call stack.
