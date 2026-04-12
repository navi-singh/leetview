---
description: EASY
---

# 228. Summary Ranges

You are given a **sorted unique** integer array `nums`.

A **range** `[a,b]` is the set of all integers from `a` to `b` (inclusive).

Return the **smallest sorted** list of ranges that **cover all the numbers in the array exactly**. That is, each element of `nums` is covered by exactly one of the ranges, and there is no integer `x` such that `x` is in one of the ranges but not in `nums`.

Each range `[a,b]` in the list should be output as:

- `"a->b"` if `a != b`
- `"a"` if `a == b`

**Example 1:**

```text
Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: The ranges are:
[0,2] --> "0->2"
[4,5] --> "4->5"
[7,7] --> "7"
```

**Example 2:**

```text
Input: nums = [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
```

**Constraints:**

- `0 <= nums.length <= 20`
- `-2^31 <= nums[i] <= 2^31 - 1`
- All the values of `nums` are **unique**.
- `nums` is sorted in ascending order.

---

## Approach: Linear Scan with Two Pointers (start/end)

Track the start and end of the current range. Iterate through the array; if the next element is exactly `end + 1`, extend the range by advancing `end`. Otherwise, close the current range, format it as a string, and start a new range. A sentinel at `i == nums.length` ensures the last range is always flushed.

#### Complexity Analysis

- **Time complexity: O(n).** Each element is visited once.
- **Space complexity: O(1).** Only two integer pointers are used (excluding the output list).

```java
package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0228_SummaryRanges {
  public List<String> summaryRanges(int[] nums) {
    List<String> res = new ArrayList<String>();
    if (nums == null || nums.length < 1) {
      return res;
    }
    int start = nums[0], end = nums[0];
    for (int i = 1; i <= nums.length; i++) {
      if (i < nums.length && nums[i] == end + 1) {
        end = nums[i];
      } else {
        if (start == end) {
          res.add(String.valueOf(start));
        } else {
          res.add(String.format("%d->%d", start, end));
        }
        if (i < nums.length) {
          start = end = nums[i];
        }
      }
    }
    return res;
  }
}
```

**Key insight:** Iterating one index past the end (`i <= nums.length`) acts as a sentinel flush, eliminating a special-case check after the loop for the last open range.
