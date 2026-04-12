---
description: MEDIUM
---

# 18. 4Sum

Given an array `nums` of `n` integers, return an array of all the **unique** quadruplets `[nums[a], nums[b], nums[c], nums[d]]` such that:

- `0 <= a, b, c, d < n`
- `a`, `b`, `c`, and `d` are **distinct**.
- `nums[a] + nums[b] + nums[c] + nums[d] == target`

You may return the answer in **any order**.

**Example 1:**

```text
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
```

**Example 2:**

```text
Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
```

**Constraints:**

- `1 <= nums.length <= 200`
- `-10^9 <= nums[i] <= 10^9`
- `-10^9 <= target <= 10^9`

---

## Approach 1: Sort + Two Nested Loops + Two Pointers with HashSet Deduplication

Extend the 3Sum approach by adding one more fixed outer loop. Sort the array. Fix `nums[i]` in the outermost loop and `nums[j]` in the second loop (skipping adjacent duplicates for `j`), then use two pointers `k` and `l` to find the remaining pair. A `HashSet` of lists automatically removes duplicate quadruplets.

#### Complexity Analysis

- **Time complexity: O(n^3).** Sorting is O(n log n); the two fixed indices give O(n^2) combinations, and the two-pointer scan for each pair is O(n).
- **Space complexity: O(n).** The `HashSet` stores the unique quadruplets found.

```java
public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    Set<List<Integer>> result = new HashSet<List<Integer>>();
    for (int i = 0; i < nums.length - 3; i++) {
        for (int j = i + 1; j < nums.length - 2; j++) {
            if (j > i + 1 && nums[j] == nums[j - 1]) continue;
            int k = j + 1, l = nums.length - 1;
            while (k < l) {
                int sum = nums[i] + nums[j] + nums[k] + nums[l];
                if (sum == target) {
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[l])));
                    k++;
                    l--;
                } else if (sum > target) l--;
                else k++;
            }
        }
    }
    return new ArrayList<List<Integer>>(result);
}
```

**Key insight:** The pattern generalizes cleanly from 3Sum — each additional target element adds one more fixed outer loop, while the innermost two-pointer scan always handles the final two elements. The HashSet deduplication handles cases the skip-duplicate guards miss.
