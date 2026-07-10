---
description: EASY
---

# 26. Remove Duplicates from Sorted Array

Given an integer array `nums` sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Return `k` after placing the final result in the first `k` slots of `nums`.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

**Example 1:**

```text
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
```

**Example 2:**

```text
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
```

**Constraints:**

- `1 <= nums.length <= 3 * 10^4`
- `-100 <= nums[i] <= 100`
- `nums` is sorted in non-decreasing order

---

## Approach 1: Two Pointers

Use a slow pointer (`newIndex`) that tracks the position of the last unique element written, and a fast pointer (`index`) that scans through the array. Whenever the fast pointer finds a value different from the element at the slow pointer, increment the slow pointer and overwrite it with the new unique value.

#### Complexity Analysis

- **Time complexity: O(n).** The fast pointer makes a single pass through the array.
- **Space complexity: O(1).** The deduplication is done in-place; no additional data structures are used.

```java
public class LC_0026_RemoveDuplicateInSortedArray {
  public int removeDuplicates(int[] nums) {
    if (nums.length < 2) return nums.length;
    int newIndex = 0;
    for (int index = 1; index < nums.length; index++) {
      if (nums[index] != nums[newIndex]) {
        newIndex += 1;
        nums[newIndex] = nums[index];
      }
    }
    return newIndex + 1;
  }
}
```

**Key insight:** Because the array is already sorted, duplicates are always adjacent. Comparing each element only to the last written unique element (`nums[newIndex]`) is sufficient to detect and skip all duplicates.
