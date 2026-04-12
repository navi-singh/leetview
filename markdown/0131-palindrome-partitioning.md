---
description: MEDIUM
---

# 131. Palindrome Partitioning

Given a string `s`, partition `s` such that every substring of the partition is a **palindrome**. Return all possible palindrome partitioning of `s`.

**Example 1:**

```text
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
```

**Example 2:**

```text
Input: s = "a"
Output: [["a"]]
```

**Constraints:**

- `1 <= s.length <= 16`
- `s` contains only lowercase English letters.

---

## Approach: Backtracking with DP Palindrome Check

Use a 2D boolean DP table `dp[i][j]` to memoize whether the substring `s[i..j]` is a palindrome. During DFS backtracking, instead of recomputing the palindrome property on every call, consult and fill the DP table in O(1). For each starting index, try all endings and only recurse when the current substring is a palindrome.

#### Complexity Analysis

- **Time complexity: O(n * 2^n).** In the worst case (all same characters), there are 2^n possible partitions, and each partition takes O(n) to copy into the result list.
- **Space complexity: O(n^2).** The DP table is n x n; recursion stack depth is O(n).

```java
package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0131_PalindromePartitioning {
  /** DP approach */
  public List<List<String>> partition(String s) {
    int len = s.length();
    boolean[][] dp = new boolean[len][len];
    List<List<String>> result = new ArrayList<>();
    dfs(result, s, 0, new ArrayList<>(), dp);
    return result;
  }

  void dfs(
      List<List<String>> result, String s, int start, List<String> currentList, boolean[][] dp) {
    if (start >= s.length()) {
      result.add(new ArrayList<>(currentList));
    }
    for (int end = start; end < s.length(); end++) {
      if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
        dp[start][end] = true;
        currentList.add(s.substring(start, end + 1));
        dfs(result, s, end + 1, currentList, dp);
        currentList.remove(currentList.size() - 1);
      }
    }
  }
}
```

**Key insight:** The condition `end - start <= 2 || dp[start+1][end-1]` handles base cases (single character and two identical characters are always palindromes) and the general case inline, eliminating a separate `isPalindrome` traversal during DFS.
