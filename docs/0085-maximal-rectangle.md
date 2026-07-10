---
description: HARD
---

# 85. Maximal Rectangle

Given a `rows x cols` binary matrix filled with `'0'`s and `'1'`s, find the largest rectangle containing only `'1'`s and return its area.

**Example 1:**

```text
Input: matrix = [["1","0","1","0","0"],
                 ["1","0","1","1","1"],
                 ["1","1","1","1","1"],
                 ["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the picture (3 wide, 2 tall).
```

**Example 2:**

```text
Input: matrix = [["0"]]
Output: 0
```

**Example 3:**

```text
Input: matrix = [["1"]]
Output: 1
```

**Constraints:**

- `rows == matrix.length`
- `cols == matrix[i].length`
- `1 <= rows, cols <= 200`
- `matrix[i][j]` is `'0'` or `'1'`

---

## Approach 1: Row-by-Row Histogram (Largest Rectangle in Histogram)

The matrix is transformed row by row into a histogram. For each cell `matrix[row][col]` that is not `'0'`, its value is replaced by the count of consecutive `'1'`s ending at that row in the same column (accumulated from the row above). Each row of the modified matrix then represents a histogram, and the largest rectangle in that histogram (solved using a monotonic stack, identical to LC 84) gives the best area achievable using bars that end at the current row. The overall maximum across all rows is the answer.

#### Complexity Analysis

- **Time complexity: O(rows * cols).** Each cell is visited once for the prefix-sum update, and the histogram computation per row is O(cols).
- **Space complexity: O(cols).** The stack used inside `MaxInRow` holds at most `cols` indices; the matrix is modified in-place.

```java
public int maximalRectangle(char[][] matrix) {
    if (matrix.length < 1 || matrix[0].length < 1) {
        return 0;
    }
    int maxSoFar = MaxInRow(matrix, 0);
    for (int row = 1; row < matrix.length; row++) {
        for (int col = 0; col < matrix[row].length; col++) {
            if (matrix[row][col] != '0') {
                matrix[row][col] = (char) ((matrix[row - 1][col] - '0' + 1) + '0');
            }
        }
        maxSoFar = Integer.max(maxSoFar, MaxInRow(matrix, row));
    }
    return maxSoFar;
}

private int MaxInRow(char[][] matrix, int row) {
    Stack<Integer> st = new Stack<Integer>();
    int left = 0, right = matrix[row].length;
    int height = 0, width = 0, maxArea = 0;
    while (left < right) {
        if (st.empty() || (matrix[row][st.peek()] - '0') < matrix[row][left] - '0') {
            st.push(left++);
        } else {
            height = matrix[row][st.pop()] - '0';
            width = st.empty() ? left : (left - st.peek() - 1);
            maxArea = Integer.max(maxArea, height * width);
        }
    }
    while (!st.empty()) {
        height = matrix[row][st.pop()] - '0';
        width = st.empty() ? left : (left - st.peek() - 1);
        maxArea = Integer.max(maxArea, height * width);
    }
    return maxArea;
}
```

**Key insight:** By treating each row as the base of a histogram whose bar heights represent how many consecutive `'1'`s lie above (including the current row), the 2D problem reduces to repeatedly solving the 1D largest-rectangle-in-histogram problem.
