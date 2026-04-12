---
description: EASY
---

# 168. Excel Sheet Column Title

Given an integer `columnNumber`, return its corresponding column title as it appears in an Excel sheet.

For example:

```
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28
...
```

**Example 1:**

```text
Input: columnNumber = 1
Output: "A"
```

**Example 2:**

```text
Input: columnNumber = 28
Output: "AB"
```

**Example 3:**

```text
Input: columnNumber = 701
Output: "ZY"
```

**Constraints:**

- `1 <= columnNumber <= 2^31 - 1`

---

## Approach: Iterative Base-26 Conversion with Offset

This is essentially a base-26 conversion, but unlike standard base-26 where digits run 0–25, Excel columns run 1–26 with no zero digit. Decrement `n` by 1 before each modulo operation to shift the range to 0–25 (mapping to A–Z), then divide by 26 and repeat. Build the result in reverse and then flip it.

#### Complexity Analysis

- **Time complexity: O(log_{26} n).** The number of characters in the result is proportional to the number of base-26 digits.
- **Space complexity: O(log_{26} n).** The StringBuilder holds the resulting column title.

```java
package com.lc;

public class LC_0168_ExcelColumnTitle {
  public String convertToTitle(int n) {
    StringBuilder sb = new StringBuilder();
    while (n > 0) {
      n--;
      sb.append((char) (n % 26 + 'A'));
      n /= 26;
    }
    sb.reverse();
    return sb.toString();
  }
}
```

**Key insight:** The `n--` before the modulo is the critical adjustment that maps the 1-indexed Excel alphabet onto the 0-indexed character array — without it, the column "Z" (26) would incorrectly produce an empty remainder instead of 'Z'.
