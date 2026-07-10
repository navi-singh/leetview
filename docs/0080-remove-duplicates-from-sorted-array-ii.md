---
description: MEDIUM
---

# 80. Remove Duplicates from Sorted Array II

Given an integer array `nums` sorted in **non-decreasing order**, remove some duplicates **in-place** such that each unique element appears **at most twice**. The **relative order** of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the **first part** of the array `nums`. More formally, if there are `k` elements after removing the duplicates, then the first `k` elements of `nums` should hold the final result. It does not matter what you leave beyond the first `k` elements.

Return `k` after placing the final result in the first `k` slots of `nums`.

Do not allocate extra space for another array. You must do this by **modifying the input array in-place** with O(1) extra memory.

**Example 1:**

```text
Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2, and 3.
```

**Example 2:**

```text
Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3, and 3.
```

**Constraints:**

- `1 <= nums.length <= 3 * 10^4`
- `-10^4 <= nums[i] <= 10^4`
- `nums` is sorted in **non-decreasing order**.

---

## Approach 1: Two Pointers (Compare Against Position k-2)

Use pointer `last` to track the write position and `next` to scan forward. For each new candidate at `next`, compare it with `nums[last - 1]` (the element two positions before the next write position). If they differ, the element appears fewer than twice in the current output window and can be written; otherwise skip it.

#### Complexity Analysis

- **Time complexity: O(n).** A single pass through the array.
- **Space complexity: O(1).** In-place modification with only two pointers.

```java
public class LC_0080_RemoveDuplicates {
  public int removeDuplicates(int[] nums) {
    if (nums.length < 3) {
      return nums.length;
    }
    int last = 1, next = 2;
    for (; next < nums.length; next++) {
      if (nums[last - 1] != nums[next]) {
        last++;
        nums[last] = nums[next];
      }
    }
    return last + 1;
  }
}
```

**Key insight:** Comparing `nums[next]` with `nums[last - 1]` (two slots behind the write head) elegantly enforces the "at most twice" rule: if they are equal the current element would appear a third consecutive time, so it is skipped.
