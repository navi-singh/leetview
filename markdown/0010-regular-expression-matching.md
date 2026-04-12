---
description: HARD
---

# 10. Regular Expression Matching

Given an input string `s` and a pattern `p`, implement regular expression matching with support for `'.'` and `'*'` where:

- `'.'` matches any single character.
- `'*'` matches zero or more of the preceding element.

The matching should cover the **entire** input string (not partial).

**Example 1:**

```text
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
```

**Example 2:**

```text
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
```

**Example 3:**

```text
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
```

**Example 4:**

```text
Input: s = "aab", p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
```

**Example 5:**

```text
Input: s = "mississippi", p = "mis*is*p*."
Output: false
```

**Constraints:**

- `1 <= s.length <= 20`
- `1 <= p.length <= 30`
- `s` contains only lowercase English letters.
- `p` contains only lowercase English letters, `'.'`, and `'*'`.
- It is guaranteed for each appearance of the character `'*'`, there will be a previous valid character to match.

---

## Approach 1: Recursive with Memoization (Top-Down DP)

Recursively check whether `s[i:]` matches `p[j:]`. A memoization table (`Status[][] tab`) caches each `(i, j)` result to avoid recomputation. When the pattern at `j+1` is `'*'`, there are two choices: skip the `x*` pair entirely (`j+2`) or consume one matching character from `s` (`i+1`) if the current character matches. Otherwise, both characters must match and we advance both pointers.

#### Complexity Analysis

- **Time complexity: O(m * n).** Each `(i, j)` state is computed at most once, where `m = s.length()` and `n = p.length()`.
- **Space complexity: O(m * n).** The memoization table has `(m+1) * (n+1)` entries.

```java
enum Status {
    TRUE,
    FALSE
}

public boolean isMatch(String s, String p) {
    Status[][] tab = new Status[s.length() + 1][p.length() + 1];
    return dp(s, p, 0, 0, tab);
}

private boolean dp(String s, String p, int i, int j, Status[][] tab) {
    if (tab[i][j] != null) return tab[i][j] == Status.TRUE;
    boolean res;
    if (j == p.length()) {
        res = i == s.length();
    } else {
        boolean first_match = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            res = (dp(s, p, i, j + 2, tab) || first_match && dp(s, p, i + 1, j, tab));
        } else {
            res = first_match && dp(s, p, i + 1, j + 1, tab);
        }
    }
    tab[i][j] = res ? Status.TRUE : Status.FALSE;
    return res;
}
```

**Key insight:** When handling `x*`, the two recursive branches represent "use zero occurrences" (`j+2`) and "use one more occurrence" (`i+1, j`) — by keeping `j` fixed in the second branch, the recursion naturally handles any number of repetitions without a loop.
