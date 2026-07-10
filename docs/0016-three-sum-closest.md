---
description: MEDIUM
---

# 16. 3Sum Closest

Given an integer array `nums` of length `n` and an integer `target`, find three integers in `nums` such that the sum is closest to `target`.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.

**Example 1:**

```text
Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
```

**Example 2:**

```text
Input: nums = [0,0,0], target = 1
Output: 0
```

**Constraints:**

- `3 <= nums.length <= 1000`
- `-1000 <= nums[i] <= 1000`
- `-10^4 <= target <= 10^4`

---

## Approach 1: Sort + Two Pointers

Sort the array. Fix the first element `nums[i]` in an outer loop, then use two pointers `j` and `k` to find the pair that brings the sum closest to `target`. Track the global closest sum and the minimum absolute difference seen so far. An early exit optimization returns immediately when `nums[i] * 3 > target`, since from that point onward the smallest possible triplet sum exceeds target and we only need to check the minimum triplet starting at `i`.

#### Complexity Analysis

- **Time complexity: O(n^2).** Sorting is O(n log n); the two-pointer inner loop is O(n) for each of the O(n) outer iterations.
- **Space complexity: O(1).** Only a constant number of variables track the closest sum.

```java
public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int closest = Integer.MAX_VALUE, diff = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length - 2; i++) {
        if (nums[i] * 3 > target) return Math.min(closest, nums[i] + nums[i + 1] + nums[i + 2]);
        int j = i + 1, k = nums.length - 1;
        while (j < k) {
            int sum = nums[i] + nums[j] + nums[k];
            int tempDiff = Math.abs(sum - target);
            if (diff > tempDiff) {
                closest = sum;
                diff = tempDiff;
            }
            if (sum > target) k--;
            else if (sum < target) j++;
            else return target;
        }
    }
    return closest;
}
```

**Key insight:** When the current sum exactly equals `target`, return immediately — no closer answer is possible. The early-exit guard `nums[i] * 3 > target` also prunes the remaining iterations once the minimum possible sum has surpassed `target`.
