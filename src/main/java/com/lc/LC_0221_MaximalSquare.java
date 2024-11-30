package com.lc;

public class LC_0221_MaximalSquare {
  public int maximalSquare(char[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
    int maxSide = 0;
    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix[0].length; i++) {
        if (matrix[i][j] == '1') {
          dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
          maxSide = Math.max(maxSide, dp[i][j]);
        }
      }
    }
    return maxSide * maxSide;
  }
}
