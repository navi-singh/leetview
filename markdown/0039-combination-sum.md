---
description: MEDIUM
---

# 39. Combination Sum

Given an array of distinct integers `candidates` and a target integer `target`, return a list of all unique combinations of `candidates` where the chosen numbers sum to `target`. You may return the combinations in any order.

The same number may be chosen from `candidates` an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to `target` is less than 150 combinations for the given input.

**Example 1:**

```text
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation: 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7. These are the only two combinations.
```

**Example 2:**

```text
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
```

**Example 3:**

```text
Input: candidates = [2], target = 1
Output: []
```

**Constraints:**

- `1 <= candidates.length <= 30`
- `2 <= candidates[i] <= 40`
- All elements of `candidates` are distinct
- `1 <= target <= 40`

---

## Approach 1: Backtracking

Sort the candidates first. Use a recursive helper that tries each candidate starting from index `start`, adds it to the current path `temp`, and recurses with a reduced target. Because a candidate can be reused, the recursive call uses the same index `i`. When the target reaches 0, the current path is a valid combination and is copied into the result. Candidates larger than the remaining target are skipped implicitly by the `if (candidates[i] <= target)` guard.

#### Complexity Analysis

- **Time complexity: O(n^(t/m)).** `n` is the number of candidates, `t` is the target, and `m` is the minimum candidate value. This bounds the depth and branching of the recursion tree.
- **Space complexity: O(t/m).** The maximum recursion depth is the target divided by the smallest candidate.

```java
public class LC_0039_CombinationSum {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
      if (candidates[i] <= target) {
        temp.add(candidates[i]);
        getCombinations(candidates, i, target - candidates[i], temp, result);
        temp.remove(temp.size() - 1);
      }
    }
  }
}
```

**Key insight:** Passing `i` (not `i + 1`) as the start index in the recursive call allows the same candidate to be reused. The `start` parameter prevents going backward, which eliminates duplicate combinations without needing a hash set.
