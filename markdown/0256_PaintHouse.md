# Description
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on… Find the minimum cost to paint all houses.

Example 1. 
 Input: 
 > [  
  [17,2,17],  
  [16,16,5],  
  [14,3,19]  
  ]
  
Output: 10  
**Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.**    
Minimum cost: 2 + 5 + 3 = 10.  
Note:
All costs are positive integers.

***Time: O(n)  
Space: O(n)***

```java
public class LC256_PaintHouse {
  public int minCost(int[][] costs) {
    if (costs == null || costs.length == 0) {
      return 0;
    }
    int[][] matrix = new int[3][costs.length];
    for (int j = 0; j < costs.length; j++) {
      if (j == 0) {
        matrix[0][j] = costs[j][0];
        matrix[1][j] = costs[j][1];
        matrix[2][j] = costs[j][2];
      } else {
        matrix[0][j] = costs[j][0] + Math.min(matrix[1][j], matrix[2][j]);
        matrix[1][j] = costs[j][1] + Math.min(matrix[0][j], matrix[2][j]);
        matrix[2][j] = costs[j][2] + Math.min(matrix[0][j], matrix[1][j]);
      }
    }
    int min = Math.min(matrix[0][costs.length - 1], matrix[1][costs.length - 1]);
    min = Math.min(min, matrix[2][costs.length - 1]);
    return min;
  }
}
```