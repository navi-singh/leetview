---
description: EASY
---

# 35. Search Insert Position

Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with `O(log n)` runtime complexity.

**Example 1:**

```text
Input: nums = [1,3,5,6], target = 5
Output: 2
```

**Example 2:**

```text
Input: nums = [1,3,5,6], target = 2
Output: 1
```

**Example 3:**

```text
Input: nums = [1,3,5,6], target = 7
Output: 4
```

**Example 4:**

```text
Input: nums = [1,3,5,6], target = 0
Output: 0
```

**Constraints:**

- `1 <= nums.length <= 10^4`
- `-10^4 <= nums[i] <= 10^4`
- `nums` contains distinct values sorted in ascending order
- `-10^4 <= target <= 10^4`

---

## Approach 1: Binary Search

Standard binary search. If `target` is found, return `mid`. If `nums[mid] > target`, narrow to the left half; otherwise narrow to the right. When the loop ends without finding `target`, `left` is the correct insertion point — it is the index of the first element greater than `target`.

#### Complexity Analysis

- **Time complexity: O(log n).** The search space halves each iteration.
- **Space complexity: O(1).** Only three integer pointers are used.

```java
public class LC_0035_SearchInsertPosition {
  public int searchInsert(int[] nums, int target) {
    int left = 0, right = nums.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }
}
```

**Key insight:** When the loop exits, `left > right`, meaning `left` points to the first position where the target would maintain sorted order. Returning `left` naturally handles both the "target too small" (returns 0) and "target too large" (returns `n`) edge cases.
