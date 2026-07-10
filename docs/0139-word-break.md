---
description: MEDIUM
---

# 139. Word Break

Given a string `s` and a dictionary of strings `wordDict`, return `true` if `s` can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

**Example 1:**

```text
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
```

**Example 2:**

```text
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
```

**Example 3:**

```text
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
```

**Constraints:**

- `1 <= s.length <= 300`
- `1 <= wordDict.length <= 1000`
- `1 <= wordDict[i].length <= 20`
- `s` and `wordDict[i]` consist of only lowercase English letters.
- All the strings of `wordDict` are **unique**.

---

## Approach: Dynamic Programming (Bottom-Up)

Create a boolean `dp` array of size `n+1` where `dp[i]` means `s[0..i-1]` can be segmented using dictionary words. Initialize `dp[0] = true` (empty string). For each position `i`, check all substrings ending at `i` — if `dp[j]` is true and `s[j..i]` is in the dictionary, mark `dp[i+1] = true`.

#### Complexity Analysis

- **Time complexity: O(n^2).** Two nested loops over the string length; substring and HashSet lookup are O(n) and O(1) on average respectively.
- **Space complexity: O(n + m).** O(n) for the DP array and O(m) for the word set where m is the total length of all dictionary words.

```java
package com.lc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC_0139_WordBreak {
  public boolean wordBreak(String s, List<String> wordDict) {
    int len = s.length();
    Set<String> strings = new HashSet<String>();
    wordDict.stream().forEach(str -> strings.add(str));

    boolean dp[] = new boolean[len + 1];
    dp[0] = true;
    for (int i = 0; i < len; i++) {
      for (int j = i; j >= 0; j--) {
        if (dp[j] && strings.contains(s.substring(j, i + 1))) {
          dp[i + 1] = true;
          break;
        }
      }
    }
    return dp[len];
  }
}
```

**Key insight:** Converting the word list to a HashSet reduces dictionary lookup to O(1) average, and the inner loop only needs to find one valid split point before breaking, making the approach efficient in practice.
