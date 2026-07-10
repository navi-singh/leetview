---
description: HARD
---

# 220. Contains Duplicate III

You are given an integer array `nums` and two integers `indexDiff` and `valueDiff`.

Find a pair of indices `(i, j)` such that:

- `i != j`
- `abs(i - j) <= indexDiff`
- `abs(nums[i] - nums[j]) <= valueDiff`

Return `true` if such pair exists or `false` otherwise.

**Example 1:**

```text
Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
Output: true
Explanation: We can choose i = 0 and j = 3. We have |i - j| = 3 <= 3 and |nums[i] - nums[j]| = 0 <= 0.
```

**Example 2:**

```text
Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
Output: false
Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return false.
```

**Constraints:**

- `2 <= nums.length <= 10^5`
- `-10^9 <= nums[i] <= 10^9`
- `1 <= indexDiff <= 10^5`
- `0 <= valueDiff <= 10^9`

---

## Approach: Sliding Window with TreeSet (Sorted Set)

Maintain a `TreeSet<Long>` of the last `k` elements. For each new element `cur`, use `floor(cur)` and `ceiling(cur)` to find the nearest existing values. If either is within `t` of `cur`, the condition is satisfied. After the check, add `cur` and evict `nums[i - k]` once the window exceeds size `k`.

#### Complexity Analysis

- **Time complexity: O(n log k).** Each insertion, deletion, and nearest-neighbor query on the `TreeSet` costs O(log k).
- **Space complexity: O(k).** The `TreeSet` holds at most `k` elements at any time.

```java
public class LC_0220_ContainsDuplicate3 {
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if (nums == null || nums.length < 1 || k < 0 || t < 0) {
      return false;
    }
    TreeSet<Long> set = new TreeSet<Long>();
    for (int i = 0; i < nums.length; i++) {
      long cur = (long) nums[i];
      if ((set.floor(cur) != null && cur <= set.floor(cur) + t)
          || (set.ceiling(cur) != null && cur >= set.ceiling(cur) - t)) {
        return true;
      }
      set.add(cur);
      if (i >= k) {
        set.remove((long) nums[i - k]);
      }
    }
    return false;
  }
}
```

**Key insight:** `TreeSet.floor` and `TreeSet.ceiling` retrieve the closest smaller and larger values in O(log k), making it possible to check the value-difference constraint in constant time per element after the tree operation.
