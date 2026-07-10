---
description: MEDIUM
---

# 33. Search in Rotated Sorted Array

There is an integer array `nums` sorted in ascending order (with distinct values). Prior to being passed to your function, `nums` is possibly rotated at an unknown pivot index `k` such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`.

Given the array `nums` after the possible rotation and an integer `target`, return the index of `target` if it is in `nums`, or `-1` if it is not in `nums`.

You must write an algorithm with `O(log n)` runtime complexity.

**Example 1:**

```text
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```

**Example 2:**

```text
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```

**Example 3:**

```text
Input: nums = [1], target = 0
Output: -1
```

**Constraints:**

- `1 <= nums.length <= 5000`
- `-10^4 <= nums[i] <= 10^4`
- All values of `nums` are unique
- `nums` is an ascending array that is possibly rotated
- `-10^4 <= target <= 10^4`

---

## Approach 1: Modified Binary Search

At each step, determine which half of the current range is guaranteed to be sorted. If `nums[left] <= nums[mid]`, the left half is sorted; check whether `target` falls in that range to decide which half to discard. Otherwise, the right half is sorted; apply the symmetric check.

#### Complexity Analysis

- **Time complexity: O(log n).** Standard binary search with one extra comparison per iteration.
- **Space complexity: O(1).** Only index variables are used.

```java
public class LC_0033_SearchInRotatedArray {
  public int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (nums[left] <= nums[mid]) {
        if (nums[mid] >= target && nums[left] <= target) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else {
        if (nums[mid] <= target && nums[right] >= target) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }
    return -1;
  }
}
```

**Key insight:** In a rotated sorted array, at least one of the two halves created by `mid` is always fully sorted. Identifying the sorted half with a single comparison allows binary search to proceed correctly despite the rotation.
