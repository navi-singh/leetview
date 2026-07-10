---
description: EASY
---

# 88. Merge Sorted Array

You are given two integer arrays `nums1` and `nums2`, sorted in non-decreasing order, and two integers `m` and `n`, representing the number of elements in `nums1` and `nums2` respectively.

Merge `nums1` and `nums2` into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array `nums1`. To accommodate this, `nums1` has a length of `m + n`, where the first `m` elements denote the elements that should be merged, and the last `n` elements are set to `0` and should be ignored. `nums2` has a length of `n`.

**Example 1:**

```text
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,3,2,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6].
```

**Example 2:**

```text
Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
```

**Example 3:**

```text
Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
```

**Constraints:**

- `nums1.length == m + n`
- `nums2.length == n`
- `0 <= m, n <= 200`
- `1 <= m + n`
- `-10^9 <= nums1[i], nums2[j] <= 10^9`

---

## Approach 1: Backwards Two-Pointer Merge

Instead of merging from the front (which would require shifting elements), we fill `nums1` from the back. Two read pointers start at the last valid elements of `nums1` (`first = m-1`) and `nums2` (`second = n-1`), and a write pointer `i` starts at `m+n-1`. At each step the larger of the two candidates is placed at `nums1[i]` and both the corresponding read pointer and `i` are decremented. The loop terminates when `i < 0`. If `nums2` is exhausted first (`second < 0`), the remaining `nums1` elements are already in place.

#### Complexity Analysis

- **Time complexity: O(m + n).** Every element is written exactly once.
- **Space complexity: O(1).** The merge is done in-place within `nums1`.

```java
public void merge(int[] nums1, int m, int[] nums2, int n) {
    int first = m - 1, second = n - 1, i = m + n - 1;
    for (; i >= 0; i--) {
        if (second < 0 || (first >= 0 && nums1[first] > nums2[second])) {
            nums1[i] = nums1[first--];
        } else {
            nums1[i] = nums2[second--];
        }
    }
}
```

**Key insight:** Filling the array from the back guarantees that we never overwrite an unprocessed element of `nums1`, eliminating the need for any extra buffer.
