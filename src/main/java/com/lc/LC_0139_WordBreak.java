package com.lc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC_0139_WordBreak {
  public boolean wordBreak(String s, List<String> wordDict) {
    int len = s.length();
    Set<String> strings = new HashSet<String>();
    wordDict.stream().forEach(str -> strings.add(str));

    boolean dp[] = new boolean[len + 1];
    dp[0] = true;
    for (int i = 0; i < len; i++) {
      for (int j = i; j >= 0; j--) {
        if (dp[j] && strings.contains(s.substring(j, i + 1))) {
          dp[i + 1] = true;
          break;
        }
      }
    }
    return dp[len];
  }
}
