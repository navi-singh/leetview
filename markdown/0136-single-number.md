---
description: EASY
---

# 136. Single Number

Given a **non-empty** array of integers `nums`, every element appears **twice** except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.

**Example 1:**

```text
Input: nums = [2,2,1]
Output: 1
```

**Example 2:**

```text
Input: nums = [4,1,2,1,2]
Output: 4
```

**Example 3:**

```text
Input: nums = [1]
Output: 1
```

**Constraints:**

- `1 <= nums.length <= 3 * 10^4`
- `-3 * 10^4 <= nums[i] <= 3 * 10^4`
- Each element in the array appears twice except for one element which appears only once.

---

## Approach: XOR Bit Manipulation

XOR every number together. Because XOR is commutative and associative, and `x ^ x = 0` and `x ^ 0 = x`, all duplicate numbers cancel out, leaving only the single number.

#### Complexity Analysis

- **Time complexity: O(n).** A single pass through all elements.
- **Space complexity: O(1).** Only one integer accumulator is needed.

```java
package com.lc;

public class LC_0136_SingleNumber {
  public int singleNumber(int[] nums) {
    int result = 0;
    for (int num : nums) {
      result ^= num;
      System.out.println(num + ":" + result);
    }
    return 0;
  }
}
```

**Key insight:** XOR is its own inverse — `a ^ a = 0` — so pairing every duplicate eliminates it from the accumulator, and only the unpaired element survives.
