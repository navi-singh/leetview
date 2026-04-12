---
description: MEDIUM
---

# 137. Single Number II

Given an integer array `nums` where every element appears **three times** except for one, which appears **exactly once**. Find the single element and return it.

You must implement a solution with a linear runtime complexity and use only constant extra space.

**Example 1:**

```text
Input: nums = [2,2,3,2]
Output: 3
```

**Example 2:**

```text
Input: nums = [0,1,0,1,0,1,99]
Output: 99
```

**Constraints:**

- `1 <= nums.length <= 3 * 10^4`
- `-2^31 <= nums[i] <= 2^31 - 1`
- Each element in `nums` appears exactly three times except for one element which appears exactly once.

---

## Approach: Bit Manipulation with Two Accumulators

Use two integers `one` and `two` to track bits that have appeared once and twice respectively. For each number:
- `one` accumulates bits seen an odd number of times that are not yet in `two`.
- `two` accumulates bits seen a second time that are not yet back in `one`.

After processing all numbers, `one` holds the bits of the element that appeared exactly once.

#### Complexity Analysis

- **Time complexity: O(n).** Single pass through the array.
- **Space complexity: O(1).** Only two integer variables are used regardless of input size.

```java
package com.lc;

public class LC_0137_SingleNumber2 {
  public int singleNumber(int[] nums) {
    int one = 0, two = 0;
    for (int i = 0; i < nums.length; i++) {
      /*
       * IF one has a number already remove it, and it does not have that number
       * appeared previously and it is not there in 2 then add it in one.
       */
      one = (one ^ nums[i]) & ~two;
      /*
       * IF two has a number already remove it, and it does not have that number
       * appeared previously and it is not there in 1 then add it in two.
       */
      two = (two ^ nums[i]) & ~one;
    }
    return one;
  }
}
```

**Key insight:** The two accumulators together implement a base-3 counter per bit: `one` holds the "ones place" and `two` holds the "twos place"; when both would become 1 (i.e., the count reaches 3), the `& ~` masking resets them both to 0, discarding triplets.
