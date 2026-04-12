---
description: MEDIUM
---

# 6. Zigzag Conversion

The string `"PAYPALISHIRING"` is written in a zigzag pattern on a given number of rows like this:

```text
P   A   H   N
A P L S I I G
Y   I   R
```

And then read line by line: `"PAHNAPLSIIGYIR"`

Write the code that will take a string and make this conversion given a number of rows:

```
string convert(string s, int numRows);
```

**Example 1:**

```text
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
```

**Example 2:**

```text
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
```

**Example 3:**

```text
Input: s = "A", numRows = 1
Output: "A"
```

**Constraints:**

- `1 <= s.length <= 1000`
- `s` consists of English letters (lower-case and upper-case), `','` and `'.'`.
- `1 <= numRows <= 1000`

---

## Approach 1: Visit by Row (Mathematical Index Jumping)

The zigzag pattern has a period of `gap = 2 * numRows - 2`. For row `i`, the primary characters in each cycle fall at indices `j + i` (stepping by `gap`). For interior rows (not first or last), there is also a diagonal character at index `j + gap - i` within the same cycle. Iterate row by row, collecting characters directly without simulating the zigzag movement.

#### Complexity Analysis

- **Time complexity: O(n).** Every character is visited exactly once across all rows.
- **Space complexity: O(n).** The result `StringBuilder` holds all `n` characters.

```java
public String convert(String s, int numRows) {
    if (numRows == 1) return s;
    StringBuilder res = new StringBuilder();
    int len = s.length();
    int gap = 2 * numRows - 2;
    for (int i = 0; i < numRows; i++) {
        for (int j = 0; j + i < len; j += gap) {
            res.append(s.charAt(j + i));
            if (i != 0 && i != numRows - 1 && j + gap - i < len) res.append(s.charAt(j + gap - i));
        }
    }
    return res.toString();
}
```

**Key insight:** The cycle length `gap = 2 * numRows - 2` lets you directly compute the position of every character in each row without simulating the zigzag — interior rows simply have two characters per cycle, at offsets `i` and `gap - i`.
