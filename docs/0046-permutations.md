---
description: MEDIUM
---

# 46. Permutations

Given an array `nums` of distinct integers, return all the possible permutations. You can return the answer in **any order**.

**Example 1:**

```text
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

**Example 2:**

```text
Input: nums = [0,1]
Output: [[0,1],[1,0]]
```

**Example 3:**

```text
Input: nums = [1]
Output: [[1]]
```

**Constraints:**

- `1 <= nums.length <= 6`
- `-10 <= nums[i] <= 10`
- All the integers of `nums` are **unique**.

---

## Approach 1: Backtracking with In-Place Swapping

At each recursive call with prefix index `k`, we try placing every remaining element at position `k` by swapping it with the element currently at `k`. After the recursive call, the swap is undone to restore the array for the next iteration. When `k == n` (all positions filled), the current arrangement is a complete permutation and is added to the result.

#### Complexity Analysis

- **Time complexity: O(n * n!).** There are `n!` permutations and each one takes `O(n)` time to copy into the result list.
- **Space complexity: O(n).** The recursion stack goes `n` levels deep. The result list itself is not counted as auxiliary space.

```java
public class LC_0046_Permutations {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    perm(nums, 0, nums.length, res);
    return res;
  }

  private void perm(int[] nums, int k, int n, List<List<Integer>> res) {
    if (k == n) {
      res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
      return;
    } else {
      for (int i = k; i < n; ++i) {
        swap(nums, i, k);
        perm(nums, k + 1, n, res);
        swap(nums, i, k);
      }
    }
  }

  private static final void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }
}
```

**Key insight:** Fixing one element at each position and recursing on the rest, with undo-swap to restore state, elegantly generates all `n!` arrangements in-place without needing a separate visited array.
