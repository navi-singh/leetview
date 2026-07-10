---
description: MEDIUM
---

# 3. Longest Substring Without Repeating Characters

Given a string `s`, find the length of the longest substring without repeating characters.

**Example 1:**

```text
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
```

**Example 2:**

```text
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```

**Example 3:**

```text
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. Note that the answer must be a substring; "pwke" is a subsequence and not a substring.
```

**Constraints:**

- `0 <= s.length <= 5 * 10^4`
- `s` consists of English letters, digits, symbols and spaces.

---

## Approach 1: Sliding Window with HashMap

Maintain a sliding window `[start, j]` of characters with no duplicates. A HashMap stores the most recent index of each character. When a duplicate is encountered at index `j`, the `start` pointer jumps past the previous occurrence of that character (using `Math.max` to ensure it never moves backward). The window size at each step is `j - start + 1`.

#### Complexity Analysis

- **Time complexity: O(n).** Each character is visited at most twice — once by `j` and at most once when `start` is updated.
- **Space complexity: O(min(m, n)).** The HashMap stores at most `min(n, m)` entries where `m` is the character set size.

```java
public int lengthOfLongestSubstring(String s) {
    int len = s.length();
    int res = 0;
    Map<Character, Integer> map = new HashMap<>();
    for (int start = 0, j = 0; j < len; j++) {
        if (map.containsKey(s.charAt(j))) {
            start = Math.max(map.get(s.charAt(j)), start);
        }
        res = Math.max(res, j - start + 1);
        map.put(s.charAt(j), j + 1);
    }
    return res;
}
```

**Key insight:** Storing `j + 1` (not `j`) as the map value means the map directly holds the new `start` position — no `+1` arithmetic is needed when jumping `start` forward, making the update a clean `start = map.get(ch)`.
