---
description: MEDIUM
---

# 45. Jump Game II

You are given a 0-indexed array of integers `nums` of length `n`. You are initially positioned at `nums[0]`.

Each element `nums[i]` represents the maximum length of a forward jump from index `i`. In other words, if you are at `nums[i]`, you can jump to any `nums[i + j]` where `0 <= j <= nums[i]` and `i + j < n`.

Return the minimum number of jumps to reach `nums[n - 1]`. The test cases are generated such that you can always reach `nums[n - 1]`.

**Example 1:**

```text
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
```

**Example 2:**

```text
Input: nums = [2,3,0,1,4]
Output: 2
```

**Constraints:**

- `1 <= nums.length <= 10^4`
- `0 <= nums[i] <= 1000`
- The generated test cases are such that you can always reach `nums[n - 1]`.

---

## Approach 1: Greedy BFS (Level-by-Level)

Think of each jump as a BFS level. `reach` tracks the furthest index reachable so far, and `lastReach` tracks the boundary of the current BFS level. When the pointer `i` crosses `lastReach`, we must have taken a new jump, so we increment the jump counter and update `lastReach` to `reach`. We also continuously update `reach` to the maximum reachable index from the current position.

#### Complexity Analysis

- **Time complexity: O(n).** The loop iterates over each index at most once.
- **Space complexity: O(1).** Only three integer variables are maintained.

```java
public class LC_0045_JumpGame2 {
  public int jump(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int jump = 0, reach = 0, lastReach = 0;
    for (int i = 0; i <= reach && i < nums.length; ++i) {
      if (i > lastReach) {
        jump++;
        lastReach = reach;
      }
      reach = Math.max(reach, i + nums[i]);
    }
    if (reach < nums.length - 1) {
      return 0;
    }
    return jump;
  }
}
```

**Key insight:** By treating the farthest reachable index as the boundary of a BFS "level," we can count the minimum number of jumps in a single linear pass without exploring every possible jump combination.
