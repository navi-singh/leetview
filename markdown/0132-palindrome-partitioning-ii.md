---
description: HARD
---

# 132. Palindrome Partitioning II

Given a string `s`, partition `s` such that every substring of the partition is a palindrome.

Return the **minimum** cuts needed for a palindrome partitioning of `s`.

**Example 1:**

```text
Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
```

**Example 2:**

```text
Input: s = "a"
Output: 0
```

**Example 3:**

```text
Input: s = "ab"
Output: 1
```

**Constraints:**

- `1 <= s.length <= 2000`
- `s` consists of lowercase English letters only.

---

## Approach: Dynamic Programming (Palindrome DP + Cut DP)

Maintain a 2D boolean `dp[j][i]` to track palindrome substrings and a 1D `cut[i]` array where `cut[i]` is the minimum cuts needed for `s[0..i]`. Initialize `cut[i] = i` (worst case: cut every character). For each pair `(j, i)`, if `s[j..i]` is a palindrome, update `cut[i]` to `min(cut[i], cut[j-1] + 1)` (or `0` if `j == 0`).

#### Complexity Analysis

- **Time complexity: O(n^2).** Two nested loops iterate over all substring pairs.
- **Space complexity: O(n^2).** The 2D palindrome table requires n^2 space; `cut` is O(n).

```java
package com.lc;

public class LC_0132_PalindromePartioning {
  public int minCut(String s) {
    int len = s.length();
    if (len < 2) {
      return 0;
    }
    boolean dp[][] = new boolean[len][len];
    int cut[] = new int[len];
    for (int i = 0; i < len; i++) {
      cut[i] = i;
      for (int j = 0; j <= i; j++) {
        if (s.charAt(i) == s.charAt(j) && (i - j <= 1 || dp[j + 1][i - 1])) {
          dp[j][i] = true;
          if (j > 0) {
            cut[i] = Math.min(cut[i], cut[j - 1] + 1);
          } else {
            cut[i] = 0;
          }
        }
      }
    }
    return cut[len - 1];
  }
}
```

**Key insight:** Combining palindrome detection and cut minimization into a single O(n^2) pass avoids a separate pre-computation step; `cut[i] = i` serves as the identity initialization representing the "cut every character" worst case.
