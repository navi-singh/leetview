package com.lc;

/**
 * here is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e.,
 * grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The
 * robot can only move either down or right at any point in time.
 *
 * <p>Given the two integers m and n, return the number of possible unique paths that the robot can
 * take to reach the bottom-right corner.
 *
 * <p>The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 * <p>Example 1:
 *
 * <p>Input: m = 3, n = 7 Output: 28 Example 2:
 *
 * <p>Input: m = 3, n = 2 Output: 3 Explanation: From the top-left corner, there are a total of 3
 * ways to reach the bottom-right corner: 1. Right -> Down -> Down 2. Down -> Down -> Right 3. Down
 * -> Right -> Down
 */
public class LC_0062_UniquePaths {
  public int uniquePaths(int m, int n) {
    int[][] grid = new int[m][n];
    // First index of every row
    for (int i = 0; i < m; i++) {
      grid[i][0] = 1;
    }
    // First index of every column
    for (int i = 1; i < n; i++) {
      grid[0][i] = 1;
    }
    // Now we use DP to evaluate total number of ways we can reach a particular cell
    // in the grid from its adjacent cell on right and top
    for (int row = 1; row < m; row++) {
      for (int col = 1; col < n; col++) {
        grid[row][col] = grid[row][col - 1] + grid[row - 1][col];
      }
    }
    return grid[m - 1][n - 1];
  }
}
