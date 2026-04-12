---
description: MEDIUM
---

# 151. Reverse Words in a String

Given an input string `s`, reverse the order of the **words**.

A **word** is defined as a sequence of non-space characters. The words in `s` will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

**Note** that `s` may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

**Example 1:**

```text
Input: s = "the sky is blue"
Output: "blue is sky the"
```

**Example 2:**

```text
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
```

**Example 3:**

```text
Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
```

**Constraints:**

- `1 <= s.length <= 10^4`
- `s` contains English letters (upper-case and lower-case), digits, and spaces `' '`.
- There is **at least one** word in `s`.

---

## Approach: Split and Reverse

Split the string on spaces, iterate from the last token to the first, appending non-space tokens to a StringBuilder with a single space separator, then trim the trailing space.

#### Complexity Analysis

- **Time complexity: O(n).** Splitting and iterating over the tokens is linear in the length of the string.
- **Space complexity: O(n).** The split array and StringBuilder each hold up to n characters.

```java
package com.lc;

public class LC_0151_ReverseWords {
  public String reverseWords(String s) {
    if (s == null || s.isEmpty()) return s;
    String arr[] = s.split(" ");
    StringBuilder sb = new StringBuilder();
    for (int i = arr.length; i >= 0; i--) {
      if (!arr[i].equals(" ")) {
        sb.append(arr[i]).append(" ");
      }
    }
    return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
  }
}
```

**Key insight:** Splitting on a single space and iterating in reverse handles word reordering in one pass; filtering out tokens equal to a space removes artifacts from multiple consecutive spaces in the original string.
