---
description: MEDIUM
---

# 77. Combinations

Given two integers `n` and `k`, return all possible combinations of `k` numbers chosen from the range `[1, n]`.

You may return the answer in **any order**.

**Example 1:**

```text
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
```

**Example 2:**

```text
Input: n = 1, k = 1
Output: [[1]]
```

**Constraints:**

- `1 <= n <= 20`
- `1 <= k <= n`

---

## Approach 1: Backtracking

Use a recursive helper that builds combinations incrementally. At each call, iterate from the current `index` to `n`, add the candidate number, recurse with `k - 1`, then remove the number (backtrack). When `k == 0` a complete combination of size `k` has been built and is added to the result.

#### Complexity Analysis

- **Time complexity: O(C(n, k) * k).** There are C(n, k) combinations and each takes O(k) time to copy.
- **Space complexity: O(k).** The recursion depth is at most `k` and the temporary list holds at most `k` elements.

```java
public class LC_0077_Combinations {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (n < 1 || n < k) {
      return res;
    }
    List<Integer> temp = new ArrayList<Integer>();
    helper(n, k, 0, temp, res);
    return res;
  }

  private void helper(int n, int k, int index, List<Integer> temp, List<List<Integer>> res) {
    if (k == 0) {
      res.add(new ArrayList<>(temp));
      return;
    }
    for (int i = index; i < n; i++) {
      temp.add(i + 1);
      helper(n, k - 1, i + 1, temp, res);
      temp.remove(temp.size() - 1);
    }
  }
}
```

**Key insight:** Iterating `i` from the current index forward (rather than from 1) ensures combinations are generated in ascending order without duplicates, making explicit deduplication unnecessary.
