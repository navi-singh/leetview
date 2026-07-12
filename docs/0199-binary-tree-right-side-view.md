# 199. Binary Tree Right Side View

Given the `root` of a binary tree, imagine yourself standing on the **right side** of it, return the values of the nodes you can see ordered from top to bottom.

## In Plain Terms

Look at the binary tree level by level and return the **rightmost node** visible at each depth. If two nodes are on the same level, only the one farthest to the right appears in the answer.

```text
    1        visible: 1
   / \
  2   3      visible: 3
   \   \
    5   4    visible: 4
```

**Example 1:**

Input: root = \[1,2,3,null,5,null,4]

Output: \[1,3,4]

Explanation:

![](https://assets.leetcode.com/uploads/2024/11/24/tmpd5jn43fs-1.png)

**Example 2:**

Input: root = \[1,2,3,4,null,null,null,5]

Output: \[1,3,4,5]

Explanation:

![](https://assets.leetcode.com/uploads/2024/11/24/tmpkpe40xeh-1.png)

**Example 3:**

Input: root = \[1,null,3]

Output: \[1,3]

**Example 4:**

Input: root = \[]

Output: \[]

#### Complexity Analysis

* **Time complexity: O(N)** since one has to visit each node.
* **Space complexity: O(H)** to keep the recursion stack, where H is a tree height. The worst-case situation is a skewed tree when H=N.

```java
class Solution {
    List<Integer> rightside = new ArrayList();

    public void helper(TreeNode node, int level) {
        if (level == rightside.size()) rightside.add(node.val);

        if (node.right != null) helper(node.right, level + 1);
        if (node.left != null) helper(node.left, level + 1);
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return rightside;

        helper(root, 0);
        return rightside;
    }
}
```

#### Python

```python
class Solution:
    def rightSideView(self, root: TreeNode) -> List[int]:
        if root is None:
            return []

        rightside = []

        def helper(node: TreeNode, level: int) -> None:
            if level == len(rightside):
                rightside.append(node.val)
            for child in [node.right, node.left]:
                if child:
                    helper(child, level + 1)

        helper(root, 0)
        return rightside
```
