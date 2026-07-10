---
description: MEDIUM
---

# 73. Set Matrix Zeroes

Given an `m x n` integer matrix `matrix`, if an element is `0`, set its entire row and column to `0`s.

You must do it **in place**.

**Example 1:**

```text
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
```

**Example 2:**

```text
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
```

**Constraints:**

- `m == matrix.length`
- `n == matrix[0].length`
- `1 <= m, n <= 200`
- `-2^31 <= matrix[i][j] <= 2^31 - 1`

**Follow up:**

- A straightforward solution using O(mn) space is probably a bad idea.
- A simple improvement uses O(m + n) space, but still not the best solution.
- Could you devise a constant space solution?

---

## Approach 1: Use First Row and Column as Markers (O(1) Space)

First record whether the first row or first column originally contained a zero (they will be used as markers and would otherwise be corrupted). Then scan the interior of the matrix: for any zero found at `(i, j)`, mark `matrix[i][0] = 0` and `matrix[0][j] = 0`. Finally, zero out rows and columns based on those markers, and handle the first row and column separately using the flags recorded earlier.

#### Complexity Analysis

- **Time complexity: O(m * n).** The matrix is scanned a constant number of times.
- **Space complexity: O(1).** No additional data structures are used; the first row and column serve as the marker arrays.

```java
public class LC_0073_SetMatrixZeroes {
  public void setZeroes(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      return;
    }
    int rows = matrix.length, cols = matrix[0].length;
    boolean isFirstRowZero = false, isFirstColZero = false;
    for (int i = 0; i < cols; i++) {
      if (matrix[0][i] == 0) {
        isFirstRowZero = true;
        break;
      }
    }
    for (int i = 0; i < rows; i++) {
      if (matrix[i][0] == 0) {
        isFirstColZero = true;
        break;
      }
    }
    for (int i = 1; i < rows; i++) {
      for (int j = 1; j < cols; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }
    for (int i = 1; i < rows; i++) {
      if (matrix[i][0] == 0) {
        updateMatrixToZero(matrix, rows, cols, i, true);
      }
    }
    for (int i = 1; i < cols; i++) {
      if (matrix[0][i] == 0) {
        updateMatrixToZero(matrix, rows, cols, i, false);
      }
    }
    if (isFirstRowZero) {
      updateMatrixToZero(matrix, rows, cols, 0, true);
    }
    if (isFirstColZero) {
      updateMatrixToZero(matrix, rows, cols, 0, false);
    }
  }

  public void updateMatrixToZero(int[][] matrix, int rows, int cols, int index, boolean isRow) {
    if (isRow) {
      for (int i = 0; i < cols; i++) {
        matrix[index][i] = 0;
      }
    } else {
      for (int i = 0; i < rows; i++) {
        matrix[i][index] = 0;
      }
    }
  }
}
```

**Key insight:** Repurposing the first row and column as boolean marker arrays achieves O(1) extra space, but the first row and column must be handled last to avoid corrupting the markers before they are read.
