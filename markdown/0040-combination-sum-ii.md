---
description: MEDIUM
---

# 40. Combination Sum II

Given a collection of candidate numbers (`candidates`) and a target number (`target`), find all unique combinations in `candidates` where the candidate numbers sum to `target`.

Each number in `candidates` may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

**Example 1:**

```text
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
  [1,1,6],
  [1,2,5],
  [1,7],
  [2,6]
]
```

**Example 2:**

```text
Input: candidates = [2,5,2,1,2], target = 5
Output:
[
  [1,2,2],
  [5]
]
```

**Constraints:**

- `1 <= candidates.length <= 100`
- `1 <= candidates[i] <= 50`
- `1 <= target <= 30`

---

## Approach 1: Backtracking with Duplicate Skipping

Sort the candidates. Use a recursive helper that iterates from `start`. Before processing, skip a candidate if it equals the previous one at the same recursion level (`i > start && candidates[i] == candidates[i - 1]`). This prevents generating duplicate combinations from identical elements. Recurse with `i + 1` (not `i`) because each number may only be used once.

#### Complexity Analysis

- **Time complexity: O(2^n).** In the worst case, every subset of candidates is explored.
- **Space complexity: O(n).** The recursion depth is at most the length of `candidates`, and `temp` holds at most `n` elements at a time.

```java
public class LC_0040_CombinationSumII {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    Arrays.sort(candidates);
    getCombinations(candidates, 0, target, temp, result);
    return result;
  }

  private void getCombinations(
      int[] candidates, int start, int target, List<Integer> temp, List<List<Integer>> result) {
    if (target == 0) {
      result.add(new ArrayList<Integer>(temp));
      return;
    }
    for (int i = start; i < candidates.length; ++i) {
      if (i > start && candidates[i] == candidates[i - 1]) continue;
      if (candidates[i] <= target) {
        temp.add(candidates[i]);
        getCombinations(candidates, i + 1, target - candidates[i], temp, result);
        temp.remove(temp.size() - 1);
      }
    }
  }
}
```

**Key insight:** The duplicate-skip condition `i > start && candidates[i] == candidates[i - 1]` works because the array is sorted — identical values are adjacent. The `i > start` guard ensures we only skip duplicates at the same level of the recursion tree, not the first use of a repeated value at a new level.
