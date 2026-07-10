---
description: MEDIUM
---

# 75. Sort Colors

Given an array `nums` with `n` objects colored red, white, or blue, sort them **in-place** so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers `0`, `1`, and `2` to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

**Example 1:**

```text
Input: nums = [2,1,2,0,0,1]
Output: [0,0,1,1,2,2]
```

**Example 2:**

```text
Input: nums = [2,0,1]
Output: [0,2,1]
Output: [0,1,2]
```

**Constraints:**

- `n == nums.length`
- `1 <= n <= 300`
- `nums[i]` is either `0`, `1`, or `2`.

**Follow up:** Could you come up with a one-pass algorithm using only constant extra space?

---

## Approach 1: Dutch National Flag (Three-Way Partition)

Maintain a `left` pointer for the next position of 0 and a `right` pointer for the next position of 2. Scan with index `i` from left to right:
- If `nums[i] == 0`, swap it to `left` and advance both `i` and `left`.
- If `nums[i] == 2`, swap it to `right` and decrement `right` (do not advance `i` since the swapped value is unexamined).
- If `nums[i] == 1`, just advance `i`.

#### Complexity Analysis

- **Time complexity: O(n).** A single pass through the array.
- **Space complexity: O(1).** In-place swaps only.

```java
public class LC_0075_SortColors {
  public void sortColors(int[] nums) {
    int left = 0, right = nums.length - 1;
    for (int i = 0; i < nums.length && i <= right; i++) {
      if (nums[i] == 0) {
        swap(nums, left, i);
        left++;
      } else if (nums[i] == 2) {
        swap(nums, right, i);
        i--;
        right--;
      }
    }
  }

  public void swap(int[] nums, int left, int right) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
  }
}
```

**Key insight:** The Dutch National Flag algorithm partitions three groups in a single pass by maintaining invariants on both ends of the array simultaneously, avoiding any need for counting or a second pass.
