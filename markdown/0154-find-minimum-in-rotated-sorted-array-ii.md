---
description: HARD
---

# 154. Find Minimum in Rotated Sorted Array II

Suppose an array of length `n` sorted in ascending order is **rotated** between `1` and `n` times.

Given the sorted rotated array `nums` that may contain **duplicates**, return the minimum element of this array.

You must decrease the overall time complexity as much as possible.

**Example 1:**

```text
Input: nums = [1,3,5]
Output: 1
```

**Example 2:**

```text
Input: nums = [2,2,2,0,1]
Output: 0
```

**Constraints:**

- `n == nums.length`
- `1 <= n <= 5000`
- `-5000 <= nums[i] <= 5000`
- `nums` is sorted and rotated between `1` and `n` times.

---

## Approach 1: Binary Search with Duplicate Handling (Compare Mid to Right)

Same as LC 153, but when `nums[mid] == nums[right]` it is impossible to determine which half the minimum is in, so decrement `right` by one to safely shrink the search space.

#### Complexity Analysis

- **Time complexity: O(log n)** average, **O(n)** worst case (all duplicates). Duplicates may force linear shrinking.
- **Space complexity: O(1).** Only index variables are used.

```java
package com.lc;

public class LC_0154_MinimumInRotatedArray {
  public int findMin(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int left = 0, right = nums.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] < nums[right]) {
        right = mid;
      } else if (nums[mid] > nums[right]) {
        left = mid + 1;
      } else {
        right--;
      }
    }
    return nums[left];
  }
```

---

## Approach 2: Binary Search with Leading Duplicate Skip

Skip duplicate values at `start` and `end` before comparing, then apply the standard rotated binary search logic.

#### Complexity Analysis

- **Time complexity: O(log n)** average, **O(n)** worst case.
- **Space complexity: O(1).** Constant extra space.

```java
  public int findMin2(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int start = 0, end = nums.length - 1;
    while (start <= end) {
      while (nums[start] == nums[end] && start != end) {
        start++;
      }
      if (nums[start] <= nums[end]) {
        return nums[start];
      }
      int mid = start + (end - start) / 2;
      if (nums[start] <= nums[mid]) {
        start = mid + 1;
      } else {
        end = mid;
      }
    }
    return -1;
  }
}
```

**Key insight:** Duplicates are the only complication compared to LC 153 — when `nums[mid] == nums[right]`, the minimum could be on either side, so the only safe move is to shrink the boundary by one; this degrades worst-case performance to O(n) but preserves correctness.
