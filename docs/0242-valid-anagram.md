---
description: EASY
---

# 242. Valid Anagram

Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.

An **anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, using all the original letters exactly once.

**Example 1:**

```text
Input: s = "anagram", t = "nagaram"
Output: true
```

**Example 2:**

```text
Input: s = "rat", t = "car"
Output: false
```

**Constraints:**

- `1 <= s.length, t.length <= 5 * 10^4`
- `s` and `t` consist of lowercase English letters.

---

## Approach: Character Frequency Counter

Use a single integer array of size 26 (one slot per lowercase letter). In a single pass over both strings simultaneously, increment the counter for each character in `s` and decrement it for the corresponding character in `t`. If all counters are zero at the end, the strings are anagrams.

#### Complexity Analysis

- **Time complexity: O(n).** One pass over both strings of length n.
- **Space complexity: O(1).** The counter array is always size 26, independent of input length.

```java
package com.lc;

public class LC_0242_ValidAnagram {
  public boolean isAnagram(String s, String t) {
    if (s == null && t == null) {
      return true;
    }
    if (s == null || t == null) {
      return false;
    }
    if (s.length() != t.length()) {
      return false;
    }
    int[] counter = new int[26];
    for (int i = 0; i < s.length(); i++) {
      counter[s.charAt(i) - 'a']++;
      counter[t.charAt(i) - 'a']--;
    }
    for (int count : counter) {
      if (count != 0) {
        return false;
      }
    }
    return true;
  }
}
```

**Key insight:** Combining the increment for `s` and decrement for `t` into a single loop pass is cleaner and equally efficient; any non-zero entry at the end means the character frequencies differ.
