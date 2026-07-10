---
description: MEDIUM
---

# 55. Jump Game

You are given an integer array `nums`. You are initially positioned at the array's **first index**, and each element in the array represents your maximum jump length at that position.

Return `true` if you can reach the last index, or `false` otherwise.

**Example 1:**

```text
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
```

**Example 2:**

```text
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
```

**Constraints:**

- `1 <= nums.length <= 10^4`
- `0 <= nums[i] <= 10^5`

---

## Approach 1: Greedy Reachability Tracking

Maintain a variable `reach` representing the furthest index reachable from any index seen so far. For each index `i`, if `reach` has not yet passed `i` and `nums[i] == 0`, we are stuck (return `false`). Otherwise update `reach = max(reach, i + nums[i])`. If `reach` at any point covers the last index, return `true` immediately.

#### Complexity Analysis

- **Time complexity: O(n).** A single left-to-right pass over the array.
- **Space complexity: O(1).** Only the `reach` variable is needed.

```java
public class LC_0055_JumpGame {
  public boolean canJump(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return true;
    }
    int reach = nums[0];
    for (int i = 0; i < nums.length; i++) {
      if (reach <= i && nums[i] == 0) {
        return false;
      }
      if (reach < i + nums[i]) {
        reach = i + nums[i];
      }
      if (reach >= nums.length - 1) {
        return true;
      }
    }
    return false;
  }
}
```

**Key insight:** We only need to track the maximum reachable index; if we ever arrive at an index that is both at the edge of our reach and has jump value 0, no further progress is possible and the answer is definitively `false`.
