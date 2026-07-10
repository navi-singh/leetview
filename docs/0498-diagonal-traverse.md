# [498. Diagonal Traverse](https://leetcode.com/problems/diagonal-traverse/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

Given an `m x n` matrix `mat`, return an array of all the elements of the array in a diagonal order.

**Example 1:** 
<img alt="" src="https://assets.leetcode.com/uploads/2021/04/10/diag1-grid.jpg" style="width: 334px; height: 334px;">

```
Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
```

**Example 2:** 

```
Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]
```

### Complexity Analysis

- Time Complexity: O(N⋅M) since we process each element of the matrix exactly once.
- Space Complexity: O(1) since we don't make use of any additional data structure. Note that the space occupied by the output array doesn't count towards the space complexity since that is a requirement of the problem itself. Space complexity comprises any additional space that we may have used to get to build the final array. For the previous solution, it was the intermediate arrays. In this solution, we don't have any additional space apart from a couple of variables.

```java
  class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int row = 0, column = 0;
        int direction = 1;
        int[] result = new int[N*M];
        int r = 0;

        while (row < N && column < M) {
            result[r++] = matrix[row][column];
            int new_row = row + (direction == 1 ? -1 : 1);
            int new_column = column + (direction == 1 ? 1 : -1);
            if (new_row < 0 || new_row == N || new_column < 0 || new_column == M) {
                if (direction == 1) {
                    row += (column == M - 1 ? 1 : 0) ;
                    column += (column < M - 1 ? 1 : 0);
                } else {
                    column += (row == N - 1 ? 1 : 0);
                    row += (row < N - 1 ? 1 : 0);
                }
                direction = 1 - direction;                                
            } else {
                row = new_row;
                column = new_column;
            }
        }
        return result;      
    }
}
```

#### Python solution 

```python
class Solution:    
    def findDiagonalOrder(self, matrix: List[List[int]]) -> List[int]:
        if not matrix or not matrix[0]:
            return []
        N, M = len(matrix), len(matrix[0])
        row, column = 0, 0
        direction = 1
        result = []
        while row < N and column < M:
            result.append(matrix[row][column])
            new_row = row + (-1 if direction == 1 else 1)
            new_column = column + (1 if direction == 1 else -1)
            if new_row < 0 or new_row == N or new_column < 0 or new_column == M:
                if direction:
                    row += (column == M - 1)
                    column += (column < M - 1)
                else:
                    column += (row == N - 1)
                    row += (row < N - 1)
                direction = 1 - direction        
            else:
                row = new_row
                column = new_column
                        
        return result
```
