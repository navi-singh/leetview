---
description: MEDIUM
---

# 198. House Robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and **it will automatically contact the police if two adjacent houses were broken into on the same night**.

Given an integer array `nums` representing the amount of money of each house, return the maximum amount of money you can rob tonight **without alerting the police**.

**Example 1:**

```text
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
```

**Example 2:**

```text
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
```

**Constraints:**

- `1 <= nums.length <= 100`
- `0 <= nums[i] <= 400`

---

## Approach: Dynamic Programming (1D DP Array)

Define `max[i]` as the maximum money that can be robbed from the first `i+1` houses. The recurrence is: `max[i] = max(max[i-2] + nums[i], max[i-1])` — either rob the current house (adding `nums[i]` to the best result two houses back) or skip it (carry forward the best result from the previous house). Seed `max[0] = nums[0]` and `max[1] = max(nums[0], nums[1])`.

#### Complexity Analysis

- **Time complexity: O(n).** A single pass through the array fills the DP table.
- **Space complexity: O(n).** A DP array of size `n` is allocated. This can be reduced to O(1) by keeping only the last two values.

```java
package com.lc;

public class LC_0198_HouseRobber {
  public int rob(int[] nums) {
    if (nums == null || nums.length < 1) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0];
    }
    int max[] = new int[nums.length];
    max[0] = nums[0];
    max[1] = Math.max(nums[0], nums[1]);
    for (int i = 2; i < nums.length; i++) {
      max[i] = Math.max(max[i - 2] + nums[i], max[i - 1]);
    }
    return max[nums.length - 1];
  }
}
```

**Key insight:** At each house the decision is binary — rob it (gain `nums[i]` but skip the previous house) or skip it (keep the best from one house earlier) — and the DP table captures the optimal sub-structure of this choice cleanly.
