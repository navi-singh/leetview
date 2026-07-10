---
description: EASY
---

# 169. Majority Element

Given an array `nums` of size `n`, return the majority element.

The majority element is the element that appears more than `⌊n / 2⌋` times. You may assume that the majority element always exists in the array.

**Example 1:**

```text
Input: nums = [3,2,3]
Output: 3
```

**Example 2:**

```text
Input: nums = [2,2,1,1,1,2,2]
Output: 2
```

**Constraints:**

- `n == nums.length`
- `1 <= n <= 5 * 10^4`
- `-10^9 <= nums[i] <= 10^9`

**Follow-up:** Could you solve the problem in linear time and in `O(1)` space?

---

## Approach: Boyer-Moore Voting Algorithm

Maintain a candidate and a count. When count reaches zero, adopt the current element as the new candidate. Increment count when the current element matches the candidate, decrement otherwise. Because the majority element appears more than half the time, it will always survive as the final candidate.

#### Complexity Analysis

- **Time complexity: O(n).** A single pass through the array is sufficient.
- **Space complexity: O(1).** Only two variables (`count` and `result`) are maintained.

```java
package com.lc;

public class LC_0169_MajorityElement {
  public int majorityElement(int[] nums) {
    int count = 0, result = 0;
    for (int i = 0; i < nums.length; i++) {
      if (count == 0) {
        result = nums[i];
        count++;
      } else if (nums[i] == result) {
        count++;
      } else {
        count--;
      }
    }
    return result;
  }
}
```

**Key insight:** Every time a non-candidate element "cancels out" a candidate element, both are effectively discarded; since the majority element has more occurrences than all others combined, it always comes out ahead.
