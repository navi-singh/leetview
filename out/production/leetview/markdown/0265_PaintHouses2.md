# Paint houses 2
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

>The cost of painting each house with a certain color is represented by a n x k cost matrix.  

>For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

 Notice

All costs are positive integers.

Example 1
>Input: costs = [[14,2,11],[11,14,5],[14,3,10]]  
Output: 10  
Explanation:
The three house use color [1,2,1] for each house. The total cost is 10.

Example 2
>Input: costs = [[5]]  
Output: 5  
Explanation:
There is only one color and one house.

***Time: O(nk)  
Space: O(1)***
```java
public class LC265_PaintHouses2 {
  public int minCostII(int[][] costs) {
    if (costs == null || costs.length < 1 || costs[0].length < 1) {
      return 0;
    }
    int preMin = 0;
    int preSecond = 0;
    int preIndex = -1;
    for (int i = 0; i < costs.length; i++) {
      int curMin = Integer.MIN_VALUE;
      int curSecond = Integer.MIN_VALUE;
      int curIndex = 0;
      for (int j = 0; j < costs[0].length; j++) {
        costs[i][j] += (preIndex == j) ? preSecond : preMin;

        if (curMin > costs[i][j]) {
          curSecond = curMin;
          curMin = costs[i][j];
          curIndex = j;
        } else if (curSecond > costs[i][j]) {
          curSecond = costs[i][j];
        }
      }
      preMin = curMin;
      preSecond = curSecond;
      preIndex = curIndex;
    }
    int result = Integer.MAX_VALUE;
    for (int i = 0; i < costs[0].length; i++) {
      if (result > costs[costs.length - 1][i]) {
        result = costs[costs.length - 1][i];
      }
    }
    return result;
  }
}
```