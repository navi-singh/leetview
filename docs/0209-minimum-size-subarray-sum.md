---
description: MEDIUM
---

# 209. Minimum Size Subarray Sum

Given an array of positive integers `nums` and a positive integer `target`, return the minimal length of a subarray whose sum is greater than or equal to `target`. If there is no such subarray, return `0` instead.

**Example 1:**

```text
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
```

**Example 2:**

```text
Input: target = 4, nums = [1,4,4]
Output: 1
```

**Example 3:**

```text
Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0
```

**Constraints:**

- `1 <= target <= 10^9`
- `1 <= nums.length <= 10^5`
- `1 <= nums[i] <= 10^4`

**Follow up:** If you have figured out the `O(n)` solution, try coding another solution of which the time complexity is `O(n log(n))`.

---

## Approach: Sliding Window

Maintain a running `sum` with a right pointer `i` expanding the window and a `start` pointer shrinking it. Whenever `sum >= target`, record the current window length and shrink from the left by advancing `start` and subtracting `nums[start]`.

#### Complexity Analysis

- **Time complexity: O(n).** Each element is added and removed from the window at most once.
- **Space complexity: O(1).** Only a constant number of variables are maintained.

```java
public class LC_0209_MinumumSizeSubarraySum {
  public int minSubArrayLen(int s, int[] nums) {
    int start = 0, sum = 0;
    int maxLen = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      while (sum >= s) {
        maxLen = Math.min(maxLen, i - start + 1);
        sum -= nums[start];
        start++;
      }
    }
    if (maxLen == Integer.MAX_VALUE) {
      return 0;
    }
    return maxLen;
  }
}
```

**Key insight:** Because all elements are positive, the window sum is monotonically increasing as the right pointer advances, making the two-pointer shrink safe and correct without missing any optimal window.
