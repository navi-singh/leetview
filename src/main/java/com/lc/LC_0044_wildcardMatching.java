package com.lc;

/**
 * Example 1:
 *
 * <p>Input: s = "aa", p = "a" Output: false Explanation: "a" does not match the entire string "aa".
 *
 * <p>Example 2:
 *
 * <p>Input: s = "aa", p = "*" Output: true Explanation: '*' matches any sequence.
 */
public class LC_0044_wildcardMatching{
  public boolean isMatch(String s, String p) {
    int i = 0, j = 0;
    int iIndex = -1, jIndex = -1;
    while (i < s.length()) {
      if (j < p.length() && ((s.charAt(i) == p.charAt(j)) || (p.charAt(j) == '?'))) {
        i++;
        j++;
      } else if (j < p.length() && p.charAt(j) == '*') {
        iIndex = i;
        jIndex = j;
        j++;
      } else if (iIndex != -1) {
        i = iIndex + 1;
        j = jIndex + 1;
        iIndex++;
      } else {
        return false;
      }
    }

    while (j < p.length() && p.charAt(j) == '*') {
      j++;
    }
    return j == p.length();
  }
}
