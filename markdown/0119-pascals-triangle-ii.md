---
description: EASY
---

# 119. Pascal's Triangle II

Given an integer `rowIndex`, return the `rowIndex`th (0-indexed) row of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it.

**Example 1:**

```text
Input: rowIndex = 3
Output: [1,3,3,1]
```

**Example 2:**

```text
Input: rowIndex = 0
Output: [1]
```

**Example 3:**

```text
Input: rowIndex = 1
Output: [1,1]
```

**Constraints:**

- `0 <= rowIndex <= 33`

**Follow up:** Could you optimize your algorithm to use only `O(rowIndex)` extra space?

---

## Approach: In-Place Single Row Update

Maintain a single list representing the current row. To advance from one row to the next, iterate the list from right to left (excluding the last element) and update each interior position in-place as the sum of its current value and its left neighbor. Then append `1` for the new last element. Going right to left prevents overwriting values needed for subsequent updates.

#### Complexity Analysis

- **Time complexity: O(rowIndex^2).** Each row requires O(row length) updates, summing to O(rowIndex^2) total.
- **Space complexity: O(rowIndex).** Only a single row of length `rowIndex + 1` is maintained.

```java
package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0119_PascalTriangle2 {
  public List<Integer> getRow(int rowIndex) {
    List<Integer> res = new ArrayList<Integer>();
    if (rowIndex < 0) {
      return res;
    }
    res.add(1);
    for (int i = 1; i <= rowIndex; i++) {
      for (int j = res.size() - 2; j >= 0; j--) {
        res.set(j + 1, res.get(j) + res.get(j + 1));
      }
      res.add(1);
    }
    return res;
  }
}
```

**Key insight:** Updating from right to left ensures that when computing position `j+1`, `res.get(j)` still holds the value from the previous row, making the in-place update correct without needing a temporary copy.
