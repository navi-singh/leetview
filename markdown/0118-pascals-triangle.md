---
description: EASY
---

# 118. Pascal's Triangle

Given an integer `numRows`, return the first `numRows` of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it.

**Example 1:**

```text
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
```

**Example 2:**

```text
Input: numRows = 1
Output: [[1]]
```

**Constraints:**

- `1 <= numRows <= 30`

---

## Approach: Iterative Row Construction

Build each row iteratively. The first and last elements of every row are always `1`. Every interior element at position `j` in row `i` is the sum of the elements at positions `j` and `j-1` in the previous row. Append each completed row to the result.

#### Complexity Analysis

- **Time complexity: O(numRows^2).** Row `i` has `i+1` elements, so the total number of elements is 1 + 2 + ... + numRows = O(numRows^2).
- **Space complexity: O(numRows^2).** The result list stores all elements.

```java
package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0118_PascalsTriangle {
  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> res = new ArrayList();
    if (numRows < 1) {
      return res;
    }
    for (int i = 0; i < numRows; i++) {
      List<Integer> temp = new ArrayList<Integer>();
      for (int j = 0; j <= i; j++) {
        if (j == 0 || j == i) {
          temp.add(1);
        } else {
          temp.add(res.get(i - 1).get(j) + res.get(i - 1).get(j - 1));
        }
      }
      res.add(temp);
    }
    return res;
  }
}
```

**Key insight:** Storing all previously built rows in the result list makes each interior element lookup O(1), eliminating the need to recompute the binomial coefficient formula.
