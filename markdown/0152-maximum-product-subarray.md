---
description: MEDIUM
---

# 152. Maximum Product Subarray

Given an integer array `nums`, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a **32-bit** integer.

**Example 1:**

```text
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
```

**Example 2:**

```text
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
```

**Constraints:**

- `1 <= nums.length <= 2 * 10^4`
- `-10 <= nums[i] <= 10`
- The product of any subarray of `nums` is guaranteed to fit in a **32-bit** integer.

---

## Approach 1: O(1) Space DP (Track Max and Min)

Track both the maximum and minimum product ending at the current position. The minimum is needed because multiplying two negatives can yield a large positive. At each step, the new max is the maximum of `(prev_max * num, prev_min * num, num)` and similarly for min. The global result is updated with the running max.

#### Complexity Analysis

- **Time complexity: O(n).** Single pass through the array.
- **Space complexity: O(1).** Only scalar variables for running max, min, and result.

```java
package com.lc;

public class LC_0152_MaximumProductSubarray {
  public int maxProduct(int[] nums) {
    if (nums == null || nums.length < 1) {
      return 0;
    }
    int minRes = nums[0], maxRes = nums[0];
    int res = nums[0];
    for (int i = 1; i < nums.length; i++) {
      int temp = maxRes;
      maxRes = Math.max(Math.max(temp * nums[i], minRes * nums[i]), nums[i]);
      minRes = Math.min(Math.min(minRes * nums[i], temp * nums[i]), nums[i]);
      res = Math.max(res, maxRes);
    }
    return res;
  }
```

---

## Approach 2: DP Arrays (Explicit Max/Min Arrays)

Use two arrays `max[i]` and `min[i]` to store the maximum and minimum product subarrays ending at index `i`. When `nums[i] > 0`, max extends from the previous max and min extends from the previous min. When `nums[i] <= 0`, they swap.

#### Complexity Analysis

- **Time complexity: O(n).** Single pass through the array.
- **Space complexity: O(n).** Two auxiliary arrays of size n.

```java
  public int maxProduct2(int[] nums) {
    if (nums == null || nums.length < 1) {
      return 0;
    }
    int[] max = new int[nums.length];
    int[] min = new int[nums.length];
    int result = nums[0];
    max[0] = min[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > 0) {
        max[i] = Math.max(nums[i], max[i - 1] * nums[i]);
        min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
      } else {
        max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
        min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
      }
      result = Math.max(result, max[i]);
    }
    return result;
  }
}
```

**Key insight:** Tracking the minimum product alongside the maximum is essential because a negative minimum multiplied by a negative number becomes the new maximum; this makes the problem tractable without examining all O(n^2) subarrays.
