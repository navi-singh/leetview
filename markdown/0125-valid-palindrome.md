---
description: EASY
---

# 125. Valid Palindrome

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

**Example 1:**

```text
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
```

**Example 2:**

```text
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
```

**Example 3:**

```text
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters. An empty string reads the same forward and backward, so it is a palindrome.
```

**Constraints:**

- `1 <= s.length <= 2 * 10^5`
- `s` consists only of printable ASCII characters.

---

## Approach: Two Pointers with In-Place Filtering

Use two pointers starting from both ends of the string. Advance each pointer past any non-alphanumeric character. Compare the characters at both pointers; if they differ, return false. Continue until the pointers meet.

#### Complexity Analysis

- **Time complexity: O(n).** Each character is examined at most once by each pointer.
- **Space complexity: O(n).** The string is lowercased into a new string; using `Character.isLetterOrDigit` on the original would achieve O(1) space.

```java
package com.lc;

public class LC_0125_ValidPalindrome {
  public boolean isPalindrome(String s) {
    if (s == null || s.isEmpty()) {
      return true;
    }
    s = s.toLowerCase();
    int start = 0, end = s.length() - 1;
    while (start < end) {
      while (start < end && !isAlNum(s.charAt(start))) {
        start++;
      }

      while (start < end && !isAlNum(s.charAt(end))) {
        end--;
      }
      if (s.charAt(start) != s.charAt(end)) {
        return false;
      }
      start++;
      end--;
    }
    return true;
  }

  boolean isAlNum(char c) {
    return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
  }
}
```

**Key insight:** Skipping non-alphanumeric characters with inner while loops before each comparison keeps the logic clean and ensures the two pointers only ever compare meaningful characters.
