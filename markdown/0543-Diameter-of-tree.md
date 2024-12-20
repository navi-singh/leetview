# [543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

Given the `root` of a binary tree, return the length of the **diameter**  of the tree.

The **diameter**  of a binary tree is the **length**  of the longest path between any two nodes in a tree. This path may or may not pass through the `root`.

The **length**  of a path between two nodes is represented by the number of edges between them.

**Example 1:** 
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/06/diamtree.jpg" style="width: 292px; height: 302px;">

```
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
```

**Example 2:** 

```
Input: root = [1,2]
Output: 1
```

**Constraints:** 

- The number of nodes in the tree is in the range `[1, 10^4]`.
- `-100 <= Node.val <= 100`

### Java
```java
class Solution {
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        this.diameter = 0;
        maxDepth(root);
        return this.diameter;
    }
    private int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        this.diameter = Math.max(this.diameter, left+right);
        return Math.max(left, right) + 1;
    }
}
```
### Python
```python
class Solution:
    def diameterOfBinaryTree(self, root: TreeNode) -> int:
        diameter = 0

        def longest_path(node):
            if not node:
                return 0
            nonlocal diameter
            # recursively find the longest path in
            # both left child and right child
            left_path = longest_path(node.left)
            right_path = longest_path(node.right)

            # update the diameter if left_path plus right_path is larger
            diameter = max(diameter, left_path + right_path)

            # return the longest one between left_path and right_path;
            # remember to add 1 for the path connecting the node and its parent
            return max(left_path, right_path) + 1

        longest_path(root)
        return diameter
```
