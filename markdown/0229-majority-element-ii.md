---
description: MEDIUM
---

# 229. Majority Element II

Given an integer array of size `n`, find all elements that appear more than `⌊ n/3 ⌋` times.

**Example 1:**

```text
Input: nums = [3,2,3]
Output: [3]
```

**Example 2:**

```text
Input: nums = [1,2]
Output: [1,2]
```

**Example 3:**

```text
Input: nums = [1,1,1,3,3,2,2,2]
Output: [1,2]
```

**Constraints:**

- `1 <= nums.length <= 5 * 10^4`
- `-10^9 <= nums[i] <= 10^9`

---

## Approach: Boyer-Moore Voting (Extended for n/3)

Because at most 2 elements can appear more than `⌊n/3⌋` times, maintain two candidates (`num1`, `num2`) and their respective counts. On the first pass, apply the Boyer-Moore voting logic: increment the matching candidate's count, assign to an empty slot, or decrement both counts. On the second pass, verify each candidate actually exceeds the `n/3` threshold.

#### Complexity Analysis

- **Time complexity: O(n).** Two linear passes over the array.
- **Space complexity: O(1).** Only a fixed number of variables regardless of input size.

```java
package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0229_MajorityElement2 {
  public List<Integer> majorityElement(int[] nums) {
    List<Integer> res = new ArrayList<Integer>();

    if (nums == null || nums.length < 1) {
      return res;
    }
    Integer num1 = null, num2 = null;
    int count1 = 0, count2 = 0;
    for (int cur : nums) {
      if (num1 != null && num1.intValue() == cur) {
        count1++;
      } else if (num2 != null && num2.intValue() == cur) {
        count2++;
      } else if (count1 == 0) {
        num1 = cur;
        count1 = 1;
      } else if (count2 == 0) {
        num2 = cur;
        count2 = 1;
      } else {
        count1--;
        count2--;
      }
    }
    count1 = count2 = 0;
    for (int cur : nums) {
      if (cur == num1) {
        count1++;
      } else if (cur == num2) {
        count2++;
      }
    }
    if (count1 > nums.length / 3) {
      res.add(num1);
    }
    if (count2 > nums.length / 3) {
      res.add(num2);
    }
    return res;
  }
}
```

**Key insight:** There can be at most two elements exceeding `n/3` frequency, so the Boyer-Moore algorithm generalizes from one candidate (majority > n/2) to two candidates, with decrement happening to both counts simultaneously when neither candidate matches.
