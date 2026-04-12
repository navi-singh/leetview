---
description: EASY
---

# 14. Longest Common Prefix

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string `""`.

**Example 1:**

```text
Input: strs = ["flower","flow","flight"]
Output: "fl"
```

**Example 2:**

```text
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```

**Constraints:**

- `1 <= strs.length <= 200`
- `0 <= strs[i].length <= 200`
- `strs[i]` consists of only lowercase English letters.

---

## Approach 1: Binary Search on Prefix Length

Binary search on the length of the common prefix, ranging from `1` to the length of the shortest string. For a candidate length `mid`, check whether all strings share the prefix `strs[0].substring(0, mid)`. Narrow the range based on whether the check passes, then return the prefix at the converged length `(low + high) / 2`.

#### Complexity Analysis

- **Time complexity: O(S * log n).** Where `S` is the sum of all string lengths and `n` is the number of strings. The binary search runs `log(minLen)` iterations, each checking all strings.
- **Space complexity: O(1).** Only index variables are used beyond the input.

```java
public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";
    int minLen = Integer.MAX_VALUE;
    for (String str : strs) minLen = Math.min(minLen, str.length());
    int low = 1;
    int high = minLen;
    while (low <= high) {
        int middle = (low + high) / 2;
        if (isCommonPrefix(strs, middle)) {
            low = middle + 1;
        } else {
            high = middle - 1;
        }
    }
    return strs[0].substring(0, (low + high) / 2);
}

private boolean isCommonPrefix(String[] strs, int len) {
    String str1 = strs[0].substring(0, len);
    for (int i = 1; i < strs.length; i++) {
        if (!strs[i].startsWith(str1)) return false;
    }
    return true;
}
```

**Key insight:** Binary searching on prefix length rather than scanning character by character reduces the number of full comparisons from O(minLen) to O(log(minLen)), which is beneficial when strings are long and share a very long common prefix.
