---
description: MEDIUM
---

# 5. Longest Palindromic Substring

Given a string `s`, return the longest palindromic substring in `s`.

**Example 1:**

```text
Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.
```

**Example 2:**

```text
Input: s = "cbbd"
Output: "bb"
```

**Example 3:**

```text
Input: s = "a"
Output: "a"
```

**Example 4:**

```text
Input: s = "ac"
Output: "a"
```

**Constraints:**

- `1 <= s.length <= 1000`
- `s` consists of only digits and English letters.

---

## Approach 1: Expand Around Center

For each index `i`, attempt to expand a palindrome outward in two ways: treating `i` as the center of an odd-length palindrome (`i, i`) and treating the gap between `i` and `i+1` as the center of an even-length palindrome (`i, i+1`). The helper `getLength` expands outward while both boundaries match and returns the palindrome length. The best `start` and `end` indices are updated whenever a longer palindrome is found.

#### Complexity Analysis

- **Time complexity: O(n^2).** For each of the `n` centers, expansion can take up to O(n) in the worst case.
- **Space complexity: O(1).** Only a fixed number of index variables are maintained.

```java
public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    int strLen = s.length();
    for (int i = 0; i < strLen; i++) {
        int evenLen = getLength(s, i, i + 1);
        int oddLen = getLength(s, i, i);
        int len = Math.max(oddLen, evenLen);
        if ((end - start) < len) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}

private int getLength(String s, int low, int high) {
    while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
        low--;
        high++;
    }
    return high - low - 1;
}
```

**Key insight:** Expanding from each center naturally handles both odd- and even-length palindromes without any extra bookkeeping — the formula `start = i - (len - 1) / 2` correctly recovers the left boundary regardless of parity.
