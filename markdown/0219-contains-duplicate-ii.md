---
description: EASY
---

# 219. Contains Duplicate II

Given an integer array `nums` and an integer `k`, return `true` if there are two distinct indices `i` and `j` in the array such that `nums[i] == nums[j]` and `abs(i - j) <= k`.

**Example 1:**

```text
Input: nums = [1,2,3,1], k = 3
Output: true
```

**Example 2:**

```text
Input: nums = [1,0,1,1], k = 1
Output: true
```

**Example 3:**

```text
Input: nums = [1,2,3,1,2,3], k = 2
Output: false
```

**Constraints:**

- `1 <= nums.length <= 10^5`
- `-10^9 <= nums[i] <= 10^9`
- `0 <= k <= 10^5`

---

## Approach: Sliding Window HashSet

Maintain a `HashSet` that holds at most `k` elements (the current window of size `k`). For each new element, attempt to add it to the set. If the add fails (element already present), a duplicate within the window is found. When the window grows beyond `k`, evict the oldest element (at index `i - k`).

#### Complexity Analysis

- **Time complexity: O(n).** Each element is inserted and potentially removed from the set once.
- **Space complexity: O(k).** The set holds at most `k + 1` elements at any point.

```java
public class LC_0219_ContainsDuplicate2 {
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    if (nums == null || nums.length < 1 || k == 0) {
      return false;
    }
    int i = 0;
    Set<Integer> dict = new HashSet<Integer>();
    for (int j = 0; j < nums.length; j++) {
      if (!dict.add(nums[j])) {
        return true;
      }
      if (dict.size() >= k + 1) {
        dict.remove(nums[i++]);
      }
    }
    return false;
  }
}
```

**Key insight:** `HashSet.add` returns `false` when the element is already present, making duplicate detection within the window a single O(1) operation rather than a separate lookup step.
