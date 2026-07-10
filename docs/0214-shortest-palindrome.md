---
description: HARD
---

# 214. Shortest Palindrome

You are given a string `s`. You can convert `s` to a palindrome by adding characters in front of it.

Return the shortest palindrome you can find by performing this transformation.

**Example 1:**

```text
Input: s = "aacecaaa"
Output: "aaacecaaa"
```

**Example 2:**

```text
Input: s = "abcd"
Output: "dcbabcd"
```

**Constraints:**

- `0 <= s.length <= 5 * 10^4`
- `s` consists of lowercase English letters only.

---

## Approach: Recursive Greedy Prefix Matching

Use two pointers: `i` starts at the beginning and `j` starts at the end. Scan `j` from right to left; whenever `s[i] == s[j]`, advance `i`. After the scan, `i` marks the length of the longest prefix of `s` that is already part of a palindrome centered at or before position `i`. The suffix `s[i:]` is not covered—prepend its reverse, recursively solve the remaining prefix `s[0:i]`, and reassemble.

#### Complexity Analysis

- **Time complexity: O(n^2).** Each recursive call processes the string in O(n) and the recursion depth can be O(n) in the worst case.
- **Space complexity: O(n).** Recursive call stack and string copies use O(n) space per level.

```java
public class LC_0214_ShortestPalindrome {
  public String shortestPalindrome(String s) {
    int i = 0;

    for (int j = s.length() - 1; j >= 0; j--) {
      if (s.charAt(i) == s.charAt(j)) {
        i++;
      }
    }

    if (i == s.length()) return s;

    String suffix = s.substring(i);
    String prefix = new StringBuilder(suffix).reverse().toString();
    String mid = shortestPalindrome(s.substring(0, i));
    return prefix + mid + suffix;
  }
}
```

**Key insight:** The two-pointer scan identifies the longest palindromic prefix in O(n); anything beyond that prefix must be mirrored and prepended, with the problem then recursively reduced to the shorter prefix.
