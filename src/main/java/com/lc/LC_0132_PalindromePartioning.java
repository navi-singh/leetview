package com.lc;

public class LC_0132_PalindromePartioning {
  public int minCut(String s) {
    int len = s.length();
    if (len < 2) {
      return 0;
    }
    boolean dp[][] = new boolean[len][len];
    int cut[] = new int[len];
    for (int i = 0; i < len; i++) {
      cut[i] = i;
      for (int j = 0; j <= i; j++) {
        if (s.charAt(i) == s.charAt(j) && (i - j <= 1 || dp[j + 1][i - 1])) {
          dp[j][i] = true;
          if (j > 0) {
            cut[i] = Math.min(cut[i], cut[j - 1] + 1);
          } else {
            cut[i] = 0;
          }
        }
      }
    }
    return cut[len - 1];
  }
}
