---
description: MEDIUM
---

# 238. Product of Array Except Self

Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the product of all the elements of `nums` except `nums[i]`.

The product of any prefix or suffix of `nums` is **guaranteed** to fit in a 32-bit integer.

You must write an algorithm that runs in `O(n)` time and **without using the division operation**.

**Example 1:**

```text
Input: nums = [1,2,3,4]
Output: [24,12,8,6]
```

**Example 2:**

```text
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
```

**Constraints:**

- `2 <= nums.length <= 10^5`
- `-30 <= nums[i] <= 30`
- The product of any prefix or suffix of `nums` is guaranteed to fit in a 32-bit integer.

---

## Approach: Left and Right Prefix Products (O(1) Extra Space)

First, fill the result array with the running left-prefix products (result[i] holds the product of all elements to the left of index i). Then do a second right-to-left pass maintaining a `rightSum` variable that accumulates the right-prefix product, multiplying it into each result entry in place.

#### Complexity Analysis

- **Time complexity: O(n).** Two linear passes over the array.
- **Space complexity: O(1).** The output array is not counted as extra space; only the single `rightSum` variable is used.

```java
package com.lc;

public class LC_0238_ProductArray {
  public int[] productExceptSelf(int[] nums) {
    if (nums == null || nums.length < 1) {
      return null;
    }
    int[] result = new int[nums.length];
    result[0] = 1;
    for (int i = 1; i < nums.length; i++) {
      result[i] = result[i - 1] * nums[i - 1];
    }
    int rightSum = 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      result[i] = result[i] * rightSum;
      rightSum *= nums[i];
    }
    return result;
  }
}
```

**Key insight:** Any element's answer is the product of everything to its left times everything to its right; by computing left-products in a forward pass and right-products in a backward pass, each answer is assembled in O(1) without division.
