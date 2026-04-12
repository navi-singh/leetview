---
description: HARD
---

# 76. Minimum Window Substring

Given two strings `s` and `t` of lengths `m` and `n` respectively, return the **minimum window substring** of `s` such that every character in `t` (including duplicates) is included in the window. If there is no such substring, return the empty string `""`.

The testcases will be generated such that the answer is **unique**.

**Example 1:**

```text
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
```

**Example 2:**

```text
Input: s = "a", t = "a"
Output: "a"
```

**Example 3:**

```text
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the latest large window is just one 'a', return empty string.
```

**Constraints:**

- `m == s.length`
- `n == t.length`
- `1 <= m, n <= 10^5`
- `s` and `t` consist of uppercase and lowercase English letters.

**Follow up:** Could you find an algorithm that runs in `O(m + n)` time?

---

## Approach 1: Sliding Window with Two HashMaps

Build a frequency map for `t`. Expand the right pointer over `s`, tracking how many required characters have been satisfied in `total`. When `total == t.length()`, the window contains all required characters. Then shrink the left pointer to minimize the window, updating the result whenever a new minimum is found.

#### Complexity Analysis

- **Time complexity: O(m + n).** Each character in `s` is added and removed from the window at most once, and building the target map is O(n).
- **Space complexity: O(m + n).** The two hash maps together hold at most all distinct characters of `s` and `t`.

```java
public class LC_0076_MinWindowSubString {

  public String minWindow(String s, String t) {
    int tLen = t.length(), sLen = s.length();
    int total = 0;
    String result = "";
    int minLen = Integer.MAX_VALUE;
    Map<Character, Integer> lookup = new HashMap<Character, Integer>();
    Map<Character, Integer> target = new HashMap<Character, Integer>();

    for (int i = 0; i < t.length(); i++) {
      target.put(t.charAt(i), target.getOrDefault(t.charAt(i), 0) + 1);
    }
    for (int left = 0, right = 0; right < sLen; right++) {
      char c = s.charAt(right);
      if (!target.containsKey(c)) {
        continue;
      }
      int count = lookup.getOrDefault(c, 0);
      if (count < target.get(c)) {
        total++;
      }
      lookup.put(c, count + 1);
      if (total == tLen) {
        while (!target.containsKey(s.charAt(left))
            || lookup.get(s.charAt(left)) > target.get(s.charAt(left))) {
          char ch = s.charAt(left);
          if (target.containsKey(ch) && lookup.get(ch) > target.get(ch)) {
            lookup.put(ch, lookup.get(ch) - 1);
          }
          left++;
        }
        if (minLen > right - left + 1) {
          minLen = right - left + 1;
          result = s.substring(left, right + 1);
        }
      }
    }
    return result;
  }
}
```

**Key insight:** The `total` counter tracks only the count of characters that are still "needed" (i.e., not yet over-satisfied), so checking `total == tLen` is an O(1) test for window validity instead of scanning the entire map.
