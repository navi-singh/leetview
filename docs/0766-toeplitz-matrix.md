# [766. Toeplitz Matrix](https://leetcode.com/problems/toeplitz-matrix/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

Given an `m x n` `matrix`, return`true`if the matrix is Toeplitz. Otherwise, return `false`.

A matrix is **Toeplitz**  if every diagonal from top-left to bottom-right has the same elements.

**Example 1:** 
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/ex1.jpg" style="width: 322px; height: 242px;">

```
Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
Output: true
Explanation:
In the above grid, thediagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.
```

**Example 2:** 
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/ex2.jpg" style="width: 162px; height: 162px;">

```
Input: matrix = [[1,2],[2,2]]
Output: false
Explanation:
The diagonal "[1, 2]" has different elements.
```

## Solution

### Complexity Analysis

**Time Complexity: O(MxN), as defined in the problem statement**

**Space Complexity: O(1)**

```python
class Solution:
    def isToeplitzMatrix(self, matrix: List[List[int]]) -> bool:
        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if i > 0 and j > 0 and matrix[i][j] != matrix[i-1][j-1]:
                    return False
        return True
```

<details>
  <summary>Java solution</summary>
  
  ```java
  class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int r = 0; r < matrix.length; ++r)
            for (int c = 0; c < matrix[0].length; ++c)
                if (r > 0 && c > 0 && matrix[r-1][c-1] != matrix[r][c])
                    return false;
        return true;
    }
}
```

</details>
