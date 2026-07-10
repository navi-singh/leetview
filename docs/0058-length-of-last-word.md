---
description: EASY
---

# 58. Length of Last Word

Given a string `s` consisting of words and spaces, return the length of the **last** word in the string.

A **word** is a maximal substring consisting of non-space characters only.

**Example 1:**

```text
Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.
```

**Example 2:**

```text
Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.
```

**Example 3:**

```text
Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.
```

**Constraints:**

- `1 <= s.length <= 10^4`
- `s` consists of only English letters and spaces `' '`.
- There will be at least one word in `s`.

---

## Approach 1: Two-Pointer from the Right

Start from the end of the string and skip trailing spaces to find the last non-space character (`firstNonSpace`). Then scan left until another space (or the beginning) is found (`left`). The length is the difference between these two pointers, with a special-case correction when `left` is 0 and the character there is not a space (i.e., the word starts at index 0).

#### Complexity Analysis

- **Time complexity: O(n).** At most two passes over the tail of the string.
- **Space complexity: O(1).** Only index variables are used.

```java
public class LC_0058_LengthLastWord {
  public int lengthOfLastWord(String s) {
    if (s == null || s.length() < 1) {
      return 0;
    }
    int firstNonSpace = s.length() - 1;
    while (s.charAt(firstNonSpace) == ' ') {
      if (firstNonSpace == 0) {
        return 0;
      }
      firstNonSpace--;
    }
    int left = firstNonSpace;
    while (left >= 0 && s.charAt(left) != ' ') {
      left--;
    }
    int length = firstNonSpace - left;
    return left == 0 ? s.charAt(left) == ' ' ? length : length + 1 : length;
  }
}
```

**Key insight:** Scanning from the right avoids the need to split the entire string; by skipping trailing spaces first and then measuring backwards to the preceding space, the last word's length is computed in minimal work.
