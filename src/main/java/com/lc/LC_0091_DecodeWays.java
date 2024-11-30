package com.lc;

public class LC_0091_DecodeWays {
  public int numDecodings(String s) {
    if (s.isEmpty() || s.charAt(0) < '1' || s.charAt(0) > '9') {
      return 0;
    }
    int[] dp = new int[s.length() + 1];
    dp[0] = dp[1] = 1;
    for (int i = 1; i < s.length(); i++) {
      int maxVal = (s.charAt(i - 1) - '0') * 10 + s.charAt(i) - '0';
      if (maxVal <= 26 && maxVal > 9) {
        dp[i + 1] += dp[i - 1];
      }
      if (s.charAt(i) != '0') {
        dp[i + 1] += dp[i];
      }
    }
    return dp[s.length()];
  }
}
