---
description: MEDIUM
---

# 153. Find Minimum in Rotated Sorted Array

Suppose an array of length `n` sorted in ascending order is **rotated** between `1` and `n` times. For example, the array `nums = [0,1,2,4,5,6,7]` might become `[4,5,6,7,0,1,2]` if it was rotated `4` times.

Given the sorted rotated array `nums` of **unique** elements, return the minimum element of this array.

You must write an algorithm that runs in `O(log n)` time.

**Example 1:**

```text
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.
```

**Example 2:**

```text
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
```

**Example 3:**

```text
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
```

**Constraints:**

- `n == nums.length`
- `1 <= n <= 5000`
- `-5000 <= nums[i] <= 5000`
- All the integers of `nums` are **unique**.
- `nums` is sorted and rotated between `1` and `n` times.

---

## Approach 1: Binary Search (Compare Mid to Right)

Compare `nums[mid]` to `nums[right]`. If `nums[mid] < nums[right]`, the minimum is in `[left, mid]`; otherwise it is in `[mid+1, right]`. Converges when `left == right`.

#### Complexity Analysis

- **Time complexity: O(log n).** Standard binary search halving.
- **Space complexity: O(1).** Only index variables are used.

```java
package com.lc;

public class LC_0153_MinimumInRoatatedArray {
  public int findMin(int[] nums) {
    if (nums == null || nums.length < 1) {
      return -1;
    }
    int left = 0, right = nums.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] < nums[right]) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return nums[left];
  }
```

---

## Approach 2: Binary Search (Compare Start to End, then Mid)

If `nums[start] <= nums[end]`, the sub-array is already sorted and `nums[start]` is the minimum. Otherwise compare `nums[start]` to `nums[mid]` to determine which half contains the rotation point.

#### Complexity Analysis

- **Time complexity: O(log n).** Binary search.
- **Space complexity: O(1).** Constant extra space.

```java
  public int findMin2(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int start = 0, end = nums.length - 1;
    while (start <= end) {
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

**Key insight:** In a rotated sorted array with unique elements, `nums[mid] < nums[right]` guarantees the right half is properly sorted and the minimum lies in `[left, mid]`; the rotation point (and minimum) must then be in the left half otherwise.
