package com.lc;

import java.util.Arrays;

public class LC_0174_DungeonGame {
  public int calculateMinimumHP(int[][] dungeon) {
    if (dungeon == null || dungeon[0].length == 0) {
      return 0;
    }
    int m = dungeon.length, n = dungeon[0].length;
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    dp[m][n - 1] = 1;
    dp[m - 1][n] = 1;
    for (int i = m - 1; i >= 0; i--) {
      for (int j = n - 1; j >= 0; j--) {
        dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
      }
    }
    return dp[0][0];
  }
}
