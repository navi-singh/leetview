---
description: HARD
---

# 164. Maximum Gap

Given an integer array `nums`, return the maximum difference between two successive elements in its sorted form. If the array contains fewer than two elements, return `0`.

You must write an algorithm that runs in linear time and uses linear extra space.

**Example 1:**

```text
Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) are both maximum gaps.
```

**Example 2:**

```text
Input: nums = [10]
Output: 0
Explanation: The array contains fewer than 2 elements, therefore return 0.
```

**Constraints:**

- `1 <= nums.length <= 10^5`
- `0 <= nums[i] <= 10^9`

---

## Approach: Bucket Sort (Pigeonhole Principle)

Find the global min and max to determine the value range, then distribute all elements into `n+1` buckets where each bucket covers a range of `(max-min)/n`. By the pigeonhole principle, at least one bucket will be empty, so the maximum gap must span across buckets — it equals the difference between the min of a bucket and the max of the previous non-empty bucket.

#### Complexity Analysis

- **Time complexity: O(n).** A single pass distributes elements into buckets, and another pass scans the buckets.
- **Space complexity: O(n).** `n+1` buckets are allocated.

```java
package com.lc;

public class LC_0164_MaximumGap {
  class Bucket {
    int min;
    int max;

    public Bucket() {
      min = Integer.MAX_VALUE;
      max = Integer.MIN_VALUE;
    }
  }

  public int maximumGap(int[] nums) {
    if (nums == null || nums.length < 2) {
      return 0;
    }
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      min = Math.min(min, nums[i]);
      max = Math.max(max, nums[i]);
    }
    Bucket buckets[] = new Bucket[nums.length + 1];
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new Bucket();
    }
    double interval = (double) nums.length / (max - min);
    for (int i = 0; i < nums.length; i++) {
      int index = (int) ((nums[i] - min) * interval);
      if (buckets[index].min == Integer.MAX_VALUE) {
        buckets[index].min = nums[i];
        buckets[index].max = nums[i];
      } else {
        buckets[index].min = Math.min(nums[i], buckets[index].min);
        buckets[index].max = Math.max(nums[i], buckets[index].max);
      }
    }
    int res = 0;
    int prev = buckets[0].max;
    for (int i = 1; i < buckets.length; i++) {
      if (buckets[i].min != Integer.MAX_VALUE) {
        res = Math.max(res, buckets[i].min - prev);
        prev = buckets[i].max;
      }
    }
    return res;
  }
}
```

**Key insight:** Placing `n` elements into `n+1` buckets guarantees at least one empty bucket, which means the answer can never come from within a single bucket — it must cross a bucket boundary, enabling O(n) max-gap computation without sorting.
