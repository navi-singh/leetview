---
description: HARD
---

# 65. Valid Number

A **valid number** can be split up into these components (in order):

1. A **decimal number** or an **integer**.
2. (Optional) An `'e'` or `'E'`, followed by an **integer**.

A **decimal number** can be split up into these components (in order):

1. (Optional) A sign character (`'+'` or `'-'`).
2. One of the following formats:
   - One or more digits, followed by a dot `'.'`.
   - One or more digits, followed by a dot `'.'`, followed by one or more digits.
   - A dot `'.'`, followed by one or more digits.

An **integer** can be split up into these components (in order):

1. (Optional) A sign character (`'+'` or `'-'`).
2. One or more digits.

For example, `["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]` are all valid numbers, while `["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]` are not valid numbers.

Given a string `s`, return `true` if `s` is a **valid number**.

**Example 1:**

```text
Input: s = "0"
Output: true
```

**Example 2:**

```text
Input: s = "e"
Output: false
```

**Example 3:**

```text
Input: s = "."
Output: false
```

**Constraints:**

- `1 <= s.length <= 20`
- `s` consists of only English letters, digits, `'+'`, `'-'`, and `'.'`

---

## Approach 1: Linear Scan / State Machine

Trim leading and trailing spaces, then scan left to right. Accept an optional sign, then consume digits. If a dot is found, continue consuming digits (either side of the dot must have digits). If an `'e'` is found after a valid number part, reset the `isNumber` flag and require at least one digit after an optional sign in the exponent.

#### Complexity Analysis

- **Time complexity: O(n).** A single pass through the string.
- **Space complexity: O(1).** Only a few pointer variables are used.

```java
public class LC_0065_ValidNumber {
  public boolean isNumber(String s) {
    int left = 0, right = s.length();
    while (left < right && s.charAt(left) == ' ') {
      left++;
    }
    while (left < right && s.charAt(right - 1) == ' ') {
      right--;
    }
    if (left < right && isPositiveOrNegative(s.charAt(left))) {
      left++;
    }
    boolean isNumber = false;
    while (left < right && isDigit(s.charAt(left))) {
      isNumber = true;
      left++;
    }
    if (left < right && s.charAt(left) == '.') {
      left++;
      while (left < right && isDigit(s.charAt(left))) {
        left++;
        isNumber = true;
      }
    }
    if (isNumber && left < right && s.charAt(left) == 'e') {
      left++;
      isNumber = false;
      if (left < right && isPositiveOrNegative(s.charAt(left))) {
        left++;
      }
      if (left < right && isDigit(s.charAt(left))) {
        isNumber = true;
        while (left < right && isDigit(s.charAt(left))) {
          left++;
        }
      }
    }
    return isNumber && left == right;
  }

  private boolean isDigit(char c) {
    int num = c - '0';
    return (num >= 0 && num <= 9);
  }

  private boolean isPositiveOrNegative(char c) {
    return (c == '+' || c == '-');
  }
}
```

**Key insight:** The `isNumber` flag enforces that at least one digit must appear either before or after a dot, and it is reset to `false` after an `'e'` to guarantee that the exponent portion also contains at least one digit.
