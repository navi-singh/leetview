---
description: MEDIUM
---

# 90. Subsets II

Given an integer array `nums` that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

**Example 1:**

```text
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
```

**Example 2:**

```text
Input: nums = [0]
Output: [[],[0]]
```

**Constraints:**

- `1 <= nums.length <= 10`
- `-10 <= nums[i] <= 10`

---

## Approach 1: Backtracking with Duplicate Skip

The array is first sorted so that duplicate values are adjacent. A recursive backtracking helper builds subsets by iterating from a given `index` forward, skipping an element whenever it equals the previous element at the same recursion depth (i.e., `i > index && nums[i] == nums[i-1]`). Each unique subset is added to a `HashSet` of lists to prevent any accidental duplicates from reaching the result.

#### Complexity Analysis

- **Time complexity: O(2^n).** In the worst case (all unique elements) we generate all subsets.
- **Space complexity: O(2^n).** The result set and recursion stack together use O(n * 2^n) space.

```java
public List<List<Integer>> subsetsWithDup(int[] nums) {
    Set<List<Integer>> res = new HashSet<List<Integer>>();
    List<Integer> temp = new ArrayList<Integer>();
    res.add(new ArrayList<Integer>(temp));
    Arrays.sort(nums);
    helper(res, temp, 0, nums);
    return new ArrayList<>(res);
}

private void helper(Set<List<Integer>> res, List<Integer> temp, int index, int[] nums) {
    for (int i = index; i < nums.length; i++) {
        if (i > index && nums[i] == nums[i - 1]) {
            continue;
        }
        temp.add(nums[i]);
        res.add(new ArrayList<Integer>(temp));
        helper(res, temp, i + 1, nums);
        temp.remove(temp.size() - 1);
    }
}
```

**Key insight:** Sorting the array and skipping duplicate siblings at the same recursion depth (when `i > index`) prevents generating duplicate subsets without needing to rely solely on the HashSet deduplication.
