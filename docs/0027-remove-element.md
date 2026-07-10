---
description: EASY
---

# 27. Remove Element

Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in `nums` in-place. The relative order of the elements may be changed. Return `k` after placing the final result in the first `k` slots of `nums`.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

**Example 1:**

```text
Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It doesn't matter what you leave beyond the returned length.
```

**Example 2:**

```text
Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.
Note that the order of those five elements can be arbitrary.
```

**Constraints:**

- `0 <= nums.length <= 100`
- `0 <= nums[i] <= 50`
- `0 <= val <= 100`

---

## Approach 1: Two Pointers (Overwrite)

Use a write pointer (`newIndex`) that only advances when a non-`val` element is found. The read pointer (`index`) scans every element. When `nums[index] != val`, copy the value to `nums[newIndex]` and increment `newIndex`. The final value of `newIndex` is the count of kept elements.

#### Complexity Analysis

- **Time complexity: O(n).** A single pass over the array.
- **Space complexity: O(1).** Modification is done in-place with two integer pointers.

```java
public class LC_0027_RemoveElementOfArray {
  public int removeElement(int[] nums, int val) {
    int newIndex = 0;
    for (int index = 0; index < nums.length; index++) {
      if (nums[index] != val) {
        nums[newIndex++] = nums[index];
      }
    }
    return newIndex;
  }
}
```

**Key insight:** There is no need to physically delete elements — simply overwrite the front portion of the array with non-`val` values. The post-increment on `newIndex` makes the code compact: the assignment and the advance happen in one expression.
