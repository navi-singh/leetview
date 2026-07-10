---
description: MEDIUM
---

# 189. Rotate Array

Given an integer array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.

**Example 1:**

```text
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
```

**Example 2:**

```text
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
```

**Constraints:**

- `1 <= nums.length <= 10^5`
- `-2^31 <= nums[i] <= 2^31 - 1`
- `0 <= k <= 10^5`

**Follow-up:**

- Try to come up with as many solutions as you can. There are at least **three** different ways to solve this problem.
- Could you do it in-place with `O(1)` extra space?

---

## Approach: Triple Reverse

Rotating right by `k` is equivalent to: (1) reverse the first `n-k` elements, (2) reverse the last `k` elements, (3) reverse the entire array. Reduce `k` modulo `n` first to handle cases where `k >= n`.

#### Complexity Analysis

- **Time complexity: O(n).** Each element is moved a constant number of times across the three reverse passes.
- **Space complexity: O(1).** All reversals are done in-place with a single swap variable.

```java
package com.lc;

public class LC_0189_RotateArray {
  public void rotate(int[] nums, int k) {
    if (nums == null) {
      return;
    }
    int len = nums.length;
    if (k > len) {
      k = k % len;
    }
    int temp;
    reverse(nums, 0, len - k - 1);
    reverse(nums, len - k, len - 1);
    reverse(nums, 0, len - 1);
  }

  private void reverse(int[] nums, int start, int end) {
    int temp;
    while (start < end) {
      temp = nums[start];
      nums[start] = nums[end];
      nums[end--] = temp;
      start++;
    }
  }
}
```

**Key insight:** Three in-place reversals achieve the same result as rotating the array, because reversing a concatenation `AB` gives `(A^r B^r)^r = BA` — exactly the right rotation.
