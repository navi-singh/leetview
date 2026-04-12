---
description: MEDIUM
---

# 48. Rotate Image

You are given an `n x n` 2D `matrix` representing an image, rotate the image by **90 degrees** (clockwise).

You have to rotate the image **in-place**, which means you have to modify the input 2D matrix directly. **Do not** allocate another 2D matrix and do the rotation.

**Example 1:**

```text
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
```

**Example 2:**

```text
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
```

**Constraints:**

- `n == matrix.length == matrix[0].length`
- `1 <= n <= 20`
- `-1000 <= matrix[i][j] <= 1000`

---

## Approach 1: Four-Way Cycle Rotation

A 90-degree clockwise rotation maps `(row, col) -> (col, n-1-row)`. By processing elements in groups of four — top, right, bottom, left — each four-element cycle can be rotated with one temporary variable in a single pass over the top-left quadrant of the matrix.

For a cell at `(row, col)`, the four positions in the cycle are:
- Top: `matrix[row][col]`
- Right: `matrix[col][n-1-row]`
- Bottom: `matrix[n-1-row][n-1-col]`
- Left: `matrix[n-1-col][row]`

The outer loop runs over rows `0` to `n/2 - 1` and the inner loop over the appropriate columns for that row (accounting for odd-sized matrices).

#### Complexity Analysis

- **Time complexity: O(n^2).** Every cell in the matrix is visited exactly once.
- **Space complexity: O(1).** Only a single temp variable is used; the rotation is in-place.

```java
public class LC_0048_RoatateImage {
  public void rotate(int[][] matrix) {
    if (matrix.length < 1 || matrix.length != matrix[0].length) {
      return;
    }
    int len = matrix.length;
    for (int row = 0; row < len / 2; ++row) {
      for (int col = 0; col < (len % 2 == 1 ? len / 2 + 1 : len / 2); ++col) {
        int temp = matrix[row][col];
        matrix[row][col] = matrix[len - col - 1][row];
        matrix[len - col - 1][row] = matrix[len - row - 1][len - col - 1];
        matrix[len - row - 1][len - col - 1] = matrix[col][len - row - 1];
        matrix[col][len - row - 1] = temp;
      }
    }
  }
}
```

**Key insight:** Grouping the four symmetric positions into a single four-way swap reduces the rotation to one pass over roughly a quarter of the matrix, achieving in-place rotation with zero extra space.
