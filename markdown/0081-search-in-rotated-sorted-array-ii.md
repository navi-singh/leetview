---
description: MEDIUM
---

# 81. Search in Rotated Sorted Array II

There is an integer array `nums` sorted in non-decreasing order (not necessarily with distinct values). Before being passed to your function, `nums` is rotated at an unknown pivot index `k` such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`.

Given the array `nums` after the possible rotation and an integer `target`, return `true` if `target` is in `nums`, or `false` if it is not in `nums`.

You must decrease the overall operation steps as much as possible.

**Example 1:**

```text
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
```

**Example 2:**

```text
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
```

**Constraints:**

- `1 <= nums.length <= 5000`
- `-10^4 <= nums[i] <= 10^4`
- `nums` is guaranteed to be rotated at some pivot
- `-10^4 <= target <= 10^4`

---

## Approach 1: Modified Binary Search

The key challenge over LC 33 is that duplicates can obscure which half is sorted. When `nums[mid] == nums[left]`, we cannot determine which side is sorted, so we increment `left` by one to shrink the search space. Otherwise we use the standard rotated-array binary search: determine which half is guaranteed sorted, then decide whether `target` falls inside that half to pick the next search range.

#### Complexity Analysis

- **Time complexity: O(n).** In the worst case (all duplicates), we increment `left` one step at a time; average case is O(log n).
- **Space complexity: O(1).** Only constant extra variables are used.

```java
public boolean search(int[] nums, int target) {
    if (nums == null || nums.length < 1) {
        return false;
    }
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return true;
        } else if (nums[mid] > nums[left]) {
            if (nums[mid] > target && target >= nums[left]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else if (nums[mid] < nums[left]) {
            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } else {
            left++;
        }
    }
    return false;
}
```

**Key insight:** When `nums[mid] == nums[left]`, it is impossible to tell which half is sorted, so incrementing `left` by one is the safest way to make progress without losing any candidates.
