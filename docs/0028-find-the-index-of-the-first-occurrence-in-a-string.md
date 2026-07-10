---
description: EASY
---

# 28. Find the Index of the First Occurrence in a String

Given two strings `haystack` and `needle`, return the index of the first occurrence of `needle` in `haystack`, or `-1` if `needle` is not part of `haystack`.

**Example 1:**

```text
Input: haystack = "hello", needle = "ll"
Output: 2
```

**Example 2:**

```text
Input: haystack = "aaaaa", needle = "bba"
Output: -1
```

**Example 3:**

```text
Input: haystack = "", needle = ""
Output: 0
```

**Constraints:**

- `0 <= haystack.length, needle.length <= 5 * 10^4`
- `haystack` and `needle` consist of only lowercase English characters

---

## Approach 1: Sliding Window (Naive / Brute Force)

For each starting index `i` in `haystack`, attempt to match all characters of `needle` by comparing characters at offsets `0..needleLength-1`. If all characters match, return `i`. If `needle` is longer than `haystack`, return `-1` immediately.

#### Complexity Analysis

- **Time complexity: O(m * n).** In the worst case (e.g., repeated characters), every starting position in the haystack triggers a full scan of the needle. `m` is the length of `haystack` and `n` is the length of `needle`.
- **Space complexity: O(1).** Only index variables are used.

```java
public class LC_0028_StrStr {
  public int strStr(String haystack, String needle) {
    if (needle == haystack) return 0;
    int hayLength = haystack.length();
    int needleLength = needle.length();
    if (needleLength > hayLength) return -1;
    int nIndex = 0;
    for (int index = 0; index < hayLength; ++index) {
      nIndex = 0;
      while (nIndex < needleLength
          && (index + nIndex) < hayLength
          && haystack.charAt(index + nIndex) == needle.charAt(nIndex)) {
        nIndex++;
      }
      if (nIndex == needleLength) return index;
    }
    return -1;
  }
}
```

**Key insight:** Resetting `nIndex` to 0 at the start of each outer iteration ensures that a partial match at one position does not carry over to the next, keeping the logic simple at the cost of potentially redundant comparisons.
