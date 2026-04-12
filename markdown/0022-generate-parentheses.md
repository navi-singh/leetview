---
description: MEDIUM
---

# 22. Generate Parentheses

Given `n` pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

**Example 1:**

```text
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
```

**Example 2:**

```text
Input: n = 1
Output: ["()"]
```

**Constraints:**

- `1 <= n <= 8`

---

## Approach 1: Backtracking (Recursive)

Track the remaining count of open (`left`) and close (`right`) parentheses that can still be placed. Add `(` when `left > 0` and add `)` when `right > left` (more open than close have been placed). The base case is when both counters reach 0. A `HashSet` deduplicates any paths that could theoretically produce identical strings.

#### Complexity Analysis

- **Time complexity: O(4^n / sqrt(n)).** The number of valid combinations is the n-th Catalan number, which grows as O(4^n / (n * sqrt(n))).
- **Space complexity: O(n).** The recursion depth is at most `2n` (length of one valid string), and the string built at each frame is at most `2n` characters.

```java
public class LC_0022_GenerateParentheses {
  public List<String> generateParenthesis(int n) {
    Set<String> res = new HashSet<>();
    helper(n, n, "", res);
    return new ArrayList<>(res);
  }

  private void helper(int left, int right, String str, Set<String> res) {
    if (left < 0 || right < 0 || left > right) return;
    if (left == 0 && right == 0) {
      res.add(str);
      return;
    }
    helper(left - 1, right, str + "(", res);
    helper(left, right - 1, str + ")", res);
  }
}
```

**Key insight:** The invariant `left > right` is pruned immediately — it means more closing brackets have been placed than opening ones, which can never be fixed. Tracking remaining counts (rather than placed counts) makes the base case a simple equality check.
