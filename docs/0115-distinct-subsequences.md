---
description: HARD
---

# 115. Distinct Subsequences

Given two strings `s` and `t`, return the number of distinct subsequences of `s` which equals `t`.

The test cases are generated so that the answer fits on a 32-bit signed integer.

**Example 1:**

```text
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
There are 3 ways to generate "rabbit" from "rabbbit":
rabbbit → rabbit (choose positions 0,1,2,3,4,6)
rabbbit → rabbit (choose positions 0,1,2,3,5,6)
rabbbit → rabbit (choose positions 0,1,2,4,5,6)
```

**Example 2:**

```text
Input: s = "babgbag", t = "bag"
Output: 5
```

**Constraints:**

- `1 <= s.length, t.length <= 1000`
- `s` and `t` consist of English letters.

---

## Approach: 2D Dynamic Programming

Define `dp[i][j]` as the number of distinct subsequences of `s[0..i-1]` that equal `t[0..j-1]`. Base case: `dp[i][0] = 1` for all `i` (empty `t` is always a subsequence). Transition: if `s[i-1] == t[j-1]`, we can either use `s[i-1]` to match `t[j-1]` (adding `dp[i-1][j-1]`) or skip it (adding `dp[i-1][j]`). If they don't match, we can only skip: `dp[i][j] = dp[i-1][j]`.

#### Complexity Analysis

- **Time complexity: O(m * n).** Two nested loops over the lengths of `s` and `t`.
- **Space complexity: O(m * n).** The DP table has `(|s|+1) x (|t|+1)` cells. Space can be optimized to O(n) with a 1D rolling array.

```java
package com.lc;

public class LC_0115_DistinctSubsequences {
  public int numDistinct(String s, String t) {
    if (s == null || t == null) {
      return 0;
    }
    int[][] dp = new int[s.length() + 1][t.length() + 1];
    for (int i = 0; i <= s.length(); i++) {
      dp[i][0] = 1;
    }
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] += dp[i - 1][j] + dp[i - 1][j - 1];
        } else {
          dp[i][j] += dp[i - 1][j];
        }
      }
    }
    return dp[s.length()][t.length()];
  }
}
```

**Key insight:** When characters match, adding both `dp[i-1][j]` (skip) and `dp[i-1][j-1]` (use) counts all distinct ways — the choice of whether to include a matching character is the source of combinatorial diversity.
