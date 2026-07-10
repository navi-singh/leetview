---
description: MEDIUM
---

# 53. Maximum Subarray

Given an integer array `nums`, find the subarray with the largest sum, and return its sum.

**Example 1:**

```text
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
```

**Example 2:**

```text
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
```

**Example 3:**

```text
Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
```

**Constraints:**

- `1 <= nums.length <= 10^5`
- `-10^4 <= nums[i] <= 10^4`

**Follow up:** If you have figured out the `O(n)` solution, try coding another solution using the **divide and conquer** approach, which is more subtle.

---

## Approach 1: Kadane's Algorithm

Maintain a running sum `tempMax`. At each element, add it to `tempMax` and update the global `max`. If `tempMax` drops below zero, reset it to zero — a negative prefix can never help a future subarray, so we effectively start a new subarray from the next element.

#### Complexity Analysis

- **Time complexity: O(n).** The array is scanned exactly once.
- **Space complexity: O(1).** Only two integer variables are used.

```java
public class LC_0053_MaximumSubarray {
  public int maxSubArray(int[] nums) {
    int max = Integer.MIN_VALUE;
    int tempMax = 0;
    for (int i = 0; i < nums.length; ++i) {
      tempMax += nums[i];
      if (tempMax > max) {
        max = tempMax;
      }
      if (tempMax < 0) {
        tempMax = 0;
      }
    }
    return max;
  }
}
```

**Key insight:** Initializing `max` to `Integer.MIN_VALUE` (not 0) correctly handles all-negative arrays, and resetting `tempMax` to 0 whenever it goes negative is the core of Kadane's algorithm — discarding any subarray whose continuation would only reduce the sum.
