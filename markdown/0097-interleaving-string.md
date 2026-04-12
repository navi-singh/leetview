---
description: MEDIUM
---

# 97. Interleaving String

Given strings `s1`, `s2`, and `s3`, find whether `s3` is formed by an interleaving of `s1` and `s2`.

An **interleaving** of two strings `s` and `t` is a configuration where `s` and `t` are divided into `n` and `m` substrings respectively, such that:

- `s = s1 + s2 + ... + sn`
- `t = t1 + t2 + ... + tm`
- `|n - m| <= 1`
- The interleaving is `s1 + t1 + s2 + t2 + s3 + t3 + ...` or `t1 + s1 + t2 + s2 + t3 + s3 + ...`

**Example 1:**

```text
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
```

**Example 2:**

```text
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
```

**Example 3:**

```text
Input: s1 = "", s2 = "", s3 = ""
Output: true
```

**Constraints:**

- `0 <= s1.length, s2.length <= 100`
- `0 <= s3.length <= 200`
- `s1`, `s2`, and `s3` consist of lowercase English letters

---

## Approach 1: Space-Optimized 2D Dynamic Programming

`dp[j]` represents whether the first `i` characters of `s1` and the first `j` characters of `s2` can form the first `i+j` characters of `s3`. The full 2D table would be `(m+1) x (n+1)`, but it is compressed to a 1D array of size `n+1` by iterating over `i` (rows) in the outer loop and reusing/overwriting `dp[j]` in place.

Transitions:
- `i == 0, j == 0`: `dp[0] = true`
- `i == 0`: `dp[j] = dp[j-1] && s2[j-1] == s3[j-1]`
- `j == 0`: `dp[0] = dp[0] && s1[i-1] == s3[i-1]`
- Otherwise: `dp[j] = (dp[j] && s1[i-1] == s3[i+j-1]) || (dp[j-1] && s2[j-1] == s3[i+j-1])`

#### Complexity Analysis

- **Time complexity: O(m * n).** Two nested loops over the lengths of `s1` and `s2`.
- **Space complexity: O(n).** Only a single 1D array of length `n+1` is maintained instead of a full 2D table.

```java
public boolean isInterleave(String s1, String s2, String s3) {
    if (s1.length() + s2.length() != s3.length()) {
        return false;
    }
    boolean[] dp = new boolean[s2.length() + 1];
    for (int i = 0; i <= s1.length(); i++) {
        for (int j = 0; j <= s2.length(); j++) {
            if (i == 0 && j == 0) {
                dp[j] = true;
            } else if (i == 0) {
                dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            } else if (j == 0) {
                dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
            } else {
                dp[j] =
                    (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
    }
    return dp[s2.length()];
}
```

**Key insight:** When `j == 0`, `dp[j]` (before overwrite) still holds the value from the previous row, representing whether `s1[0..i-1]` alone forms `s3[0..i-1]`, so the 1D array correctly captures both the "take from s1" and "take from s2" transitions without a 2D table.
