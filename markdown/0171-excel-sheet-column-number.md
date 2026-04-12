---
description: EASY
---

# 171. Excel Sheet Column Number

Given a string `columnTitle` that represents the column title as appears in an Excel sheet, return its corresponding column number.

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
Input: columnTitle = "A"
Output: 1
```

**Example 2:**

```text
Input: columnTitle = "AB"
Output: 28
```

**Example 3:**

```text
Input: columnTitle = "ZY"
Output: 701
```

**Constraints:**

- `1 <= columnTitle.length <= 7`
- `columnTitle` consists only of uppercase English letters.
- `columnTitle` is in the range `["A", "ZZZ...Z"]`.

---

## Approach: Left-to-Right Base-26 Accumulation

Process each character from left to right. At each step, multiply the running result by 26 (shifting the existing digits up one place) and add the numeric value of the current character (`'A'` = 1, `'B'` = 2, …, `'Z'` = 26). This is the inverse of the column-title conversion in problem 168.

#### Complexity Analysis

- **Time complexity: O(n).** Where `n` is the length of the column title string.
- **Space complexity: O(1).** Only a single integer accumulator is used.

```java
package com.lc;

public class LC_0171_ExcelSheetNumber {
  public int titleToNumber(String s) {
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
      res = res * 26 + (s.charAt(i) - 'A' + 1);
    }
    return res;
  }
}
```

**Key insight:** Treating the column title as a base-26 number read left to right — where 'A'=1 through 'Z'=26 — allows a standard Horner's method accumulation to produce the correct column number in a single pass.
