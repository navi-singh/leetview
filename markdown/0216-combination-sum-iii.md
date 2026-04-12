---
description: MEDIUM
---

# 216. Combination Sum III

Find all valid combinations of `k` numbers that sum up to `n` such that the following conditions are true:

- Only numbers `1` through `9` are used.
- Each number is used at most once.

Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

**Example 1:**

```text
Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.
```

**Example 2:**

```text
Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.
```

**Example 3:**

```text
Input: k = 4, n = 1
Output: []
Explanation: There are no valid combinations.
```

**Constraints:**

- `2 <= k <= 9`
- `1 <= n <= 60`

---

## Approach: Backtracking

Starting from digit `1`, try adding each digit `i` (from the current start up to `9`) to a temporary list and recursively reduce the remaining sum. If the list reaches size `k` and the remaining sum is `0`, a valid combination is found. If the remaining sum goes negative, backtrack immediately.

#### Complexity Analysis

- **Time complexity: O(C(9, k)).** The search space is bounded by the number of k-combinations from 9 digits.
- **Space complexity: O(k).** The recursion depth and temporary list are at most size `k`.

```java
public class LC_0216_Combination3 {
  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> temp = new ArrayList<Integer>();
    helper(k, n, temp, result, 1);
    return result;
  }

  private void helper(int k, int sum, List<Integer> temp, List<List<Integer>> result, int i) {
    if (sum < 0) {
      return;
    }
    if (k == temp.size() && sum == 0) {
      result.add(new ArrayList<Integer>(temp));
      return;
    }

    for (; i < 10; i++) {
      temp.add(i);
      helper(k, sum - i, temp, result, i + 1);
      temp.remove(temp.size() - 1);
    }
  }
}
```

**Key insight:** Starting each recursive call's loop from `i + 1` (never reusing digits) and pruning when `sum < 0` ensures the search only explores valid, non-duplicate combinations without needing a visited set.
