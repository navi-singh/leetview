---
description: MEDIUM
---

# 15. 3Sum

Given an integer array `nums`, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j`, `i != k`, and `j != k`, and `nums[i] + nums[j] + nums[k] == 0`.

Notice that the solution set must not contain duplicate triplets.

**Example 1:**

```text
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
```

**Example 2:**

```text
Input: nums = []
Output: []
```

**Example 3:**

```text
Input: nums = [0]
Output: []
```

**Constraints:**

- `0 <= nums.length <= 3000`
- `-10^5 <= nums[i] <= 10^5`

---

## Approach 1: Sort + Two Pointers with HashSet Deduplication

Sort the array. Fix the first element `nums[i]` in an outer loop, then use two pointers `j` (from `i+1`) and `k` (from the end) to find pairs summing to `-nums[i]`. When the sum is too large, decrement `k`; when too small, increment `j`; when equal, record the triplet and move both pointers. A `HashSet` of lists is used to eliminate duplicate triplets automatically. Additionally, skip duplicate values of `k` before processing each step.

#### Complexity Analysis

- **Time complexity: O(n^2).** Sorting is O(n log n); the two-pointer scan for each fixed `i` is O(n), giving O(n^2) overall.
- **Space complexity: O(n).** The `HashSet` can hold up to O(n) unique triplets.

```java
public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    Set<List<Integer>> result = new HashSet<>();
    for (int i = 0; i < nums.length - 2; i++) {
        int j = i + 1, k = nums.length - 1;
        while (j < k) {
            if (k < nums.length - 1 && nums[k] == nums[k + 1]) {
                k--;
                continue;
            }
            if (nums[i] + nums[j] + nums[k] > 0) k--;
            else if (nums[i] + nums[j] + nums[k] < 0) j++;
            else {
                List<Integer> lis = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                result.add(lis);
                j++;
                k--;
            }
        }
    }
    return new ArrayList<List<Integer>>(result);
}
```

**Key insight:** Sorting first is the key enabler — it allows a two-pointer scan to replace the inner O(n^2) brute-force loop, and it makes duplicate detection straightforward since equal elements are adjacent.
