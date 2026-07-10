---
description: EASY
---

# 108. Convert Sorted Array to Binary Search Tree

Given an integer array `nums` where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

**Example 1:**

```text
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted.
```

**Example 2:**

```text
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
```

**Constraints:**

- `1 <= nums.length <= 10^4`
- `-10^4 <= nums[i] <= 10^4`
- `nums` is sorted in ascending order.

---

## Approach: Recursive Binary Split

Always choose the middle element of the current subarray as the root. This guarantees height balance because it splits the remaining elements as evenly as possible between the left and right subtrees. Recurse on the left half for the left subtree and the right half for the right subtree.

#### Complexity Analysis

- **Time complexity: O(n).** Every element is visited exactly once to create a node.
- **Space complexity: O(log n).** The recursion stack depth is O(log n) because the array is always halved; the output tree itself takes O(n).

```java
package com.lc;

public class LC_0108_ConvertSortedArrayToBST {
  public TreeNode sortedArrayToBST(int[] nums) {
    if (nums.length == 0) {
      return null;
    }
    return helper(nums, 0, nums.length - 1);
  }

  private TreeNode helper(int[] nums, int start, int end) {
    if (start > end) {
      return null;
    }
    int mid = start + (end - start) / 2;
    TreeNode node = new TreeNode(nums[mid]);
    node.left = helper(nums, start, mid - 1);
    node.right = helper(nums, mid + 1, end);
    return node;
  }
}
```

**Key insight:** Picking the midpoint as the root at every level of recursion mirrors a binary search, ensuring the left and right subtrees never differ in size by more than one node.
