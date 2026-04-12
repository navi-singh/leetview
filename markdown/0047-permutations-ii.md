---
description: MEDIUM
---

# 47. Permutations II

Given a collection of numbers, `nums`, that **might contain duplicates**, return all possible unique permutations **in any order**.

**Example 1:**

```text
Input: nums = [1,1,2]
Output: [[1,1,2],[1,2,1],[2,1,1]]
```

**Example 2:**

```text
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

**Constraints:**

- `1 <= nums.length <= 8`
- `-10 <= nums[i] <= 10`

---

## Approach 1: Backtracking with Duplicate Skip

This extends the swap-based backtracking from LC 46. Before swapping element `i` into position `k`, the helper `needSwap` checks whether any element between `k` and `i` has the same value as `nums[i]`. If it does, swapping would produce a duplicate permutation, so the swap is skipped. This guards against generating the same arrangement from two equal elements.

#### Complexity Analysis

- **Time complexity: O(n * n!).** In the worst case (all distinct), `n!` permutations are generated. The `needSwap` check adds an `O(n)` linear scan per node, but the dominant term remains `O(n * n!)`.
- **Space complexity: O(n).** The recursion depth is at most `n`.

```java
public class LC_0047_PermutationsII {
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
        if (needSwap(nums, i, k)) {
          LC_0047_PermutationsII.swap(nums, i, k);
          perm(nums, k + 1, n, res);
          LC_0047_PermutationsII.swap(nums, i, k);
        }
      }
    }
  }

  private static final void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }

  private static final boolean needSwap(int[] nums, int i, int k) {
    for (int index = k; index < i; ++index) {
      if (nums[index] == nums[i]) {
        return false;
      }
    }
    return true;
  }
}
```

**Key insight:** The `needSwap` guard enforces that within the range `[k, i)` no element equals `nums[i]`, ensuring each distinct value is placed at position `k` exactly once and eliminating duplicate permutations without a separate hash set.
