---
description: MEDIUM
---

# 128. Longest Consecutive Sequence

Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in `O(n)` time.

**Example 1:**

```text
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
```

**Example 2:**

```text
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
```

**Constraints:**

- `0 <= nums.length <= 10^5`
- `-10^9 <= nums[i] <= 10^9`

---

## Approach: HashSet Expansion

Add all numbers to a HashSet. For each number, expand outward (both up and down) removing visited numbers to count the streak length. Removing visited elements prevents re-counting and ensures each element is processed at most once.

#### Complexity Analysis

- **Time complexity: O(n).** Each element is added and removed from the set at most once, making the total work linear.
- **Space complexity: O(n).** The HashSet stores all `n` elements.

```java
package com.lc;

import java.util.HashSet;
import java.util.Set;

public class LC_0128_LongestConsecutiveSequence {
  public int longestConsecutive(int[] nums) {
    Set<Integer> numSet = new HashSet<Integer>();
    int max = 0;
    for (int num : nums) {
      numSet.add(num);
    }
    for (int num : nums) {
      int count = 1;
      int down = num - 1;
      while (numSet.contains(down)) {
        numSet.remove(down);
        down--;
        count++;
      }
      int up = num + 1;
      while (numSet.contains(up)) {
        numSet.remove(up);
        up++;
        count++;
      }
      max = Math.max(max, count);
    }
    return max;
  }
}
```

**Key insight:** By removing elements as they are visited, each number participates in exactly one streak expansion, keeping the overall time complexity O(n) despite the nested while loops.
