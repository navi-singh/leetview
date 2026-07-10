---
description: HARD
---

# 44. Wildcard Matching

Given an input string `s` and a pattern `p`, implement wildcard pattern matching with support for `'?'` and `'*'` where:

- `'?'` matches any single character.
- `'*'` matches any sequence of characters (including the empty sequence).

The matching should cover the **entire** input string (not partial).

**Example 1:**

```text
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
```

**Example 2:**

```text
Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
```

**Example 3:**

```text
Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
```

**Constraints:**

- `0 <= s.length, p.length <= 2000`
- `s` contains only lowercase English letters.
- `p` contains only lowercase English letters, `'?'`, or `'*'`.

---

## Approach 1: Two-Pointer Greedy with Backtracking

Use two pointers `i` (into `s`) and `j` (into `p`). When a `'*'` is encountered in `p`, record the positions `iIndex` and `jIndex`. If a mismatch occurs later, backtrack: advance `iIndex` by one (the `'*'` consumes one more character of `s`) and reset `j` to just after the `'*'`. This greedy backtracking always tries to consume as few characters as possible for `'*'` first, then expands if needed.

#### Complexity Analysis

- **Time complexity: O(s * p).** In the worst case each `'*'` may trigger backtracking for every character in `s`.
- **Space complexity: O(1).** Only a constant number of pointer variables are used.

```java
public class LC_0044_wildcardMatching {
  public boolean isMatch(String s, String p) {
    int i = 0, j = 0;
    int iIndex = -1, jIndex = -1;
    while (i < s.length()) {
      if (j < p.length() && ((s.charAt(i) == p.charAt(j)) || (p.charAt(j) == '?'))) {
        i++;
        j++;
      } else if (j < p.length() && p.charAt(j) == '*') {
        iIndex = i;
        jIndex = j;
        j++;
      } else if (iIndex != -1) {
        i = iIndex + 1;
        j = jIndex + 1;
        iIndex++;
      } else {
        return false;
      }
    }

    while (j < p.length() && p.charAt(j) == '*') {
      j++;
    }
    return j == p.length();
  }
}
```

**Key insight:** Storing the last `'*'` position as a backtrack checkpoint lets us greedily try the shortest possible match for each `'*'` and expand one character at a time on failure, avoiding the need for a full DP table.
