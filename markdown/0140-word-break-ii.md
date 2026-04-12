---
description: HARD
---

# 140. Word Break II

Given a string `s` and a dictionary of strings `wordDict`, add spaces in `s` to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in **any order**.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

**Example 1:**

```text
Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
```

**Example 2:**

```text
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
```

**Example 3:**

```text
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
```

**Constraints:**

- `1 <= s.length <= 20`
- `1 <= wordDict.length <= 1000`
- `1 <= wordDict[i].length <= 10`
- `s` and `wordDict[i]` consist of only lowercase English letters.
- All the strings of `wordDict` are **unique**.
- Input is generated in a way that the length of the answer doesn't exceed 10^5.

---

## Approach: Backtracking with Memoization

Use recursive backtracking: at each starting index, try every dictionary word that matches the prefix. If the entire string is consumed, record the path. Memoize results for each starting index to avoid recomputing overlapping subproblems.

#### Complexity Analysis

- **Time complexity: O(n * 2^n).** In the worst case, exponentially many sentences can be produced.
- **Space complexity: O(n * 2^n).** Space for storing all result sentences plus the recursion stack.

```java
package com.lc;

public class LC_0140_WordBreak2 {}
```

**Key insight:** Unlike Word Break I (which only needs a boolean answer), Word Break II must enumerate all solutions, so backtracking is necessary; memoizing the list of suffixes reachable from each index avoids redundant recursive calls on shared substrings.
