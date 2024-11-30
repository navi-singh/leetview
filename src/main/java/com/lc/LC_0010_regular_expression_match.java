package com.lc;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support
 * for '.' and '*' where:
 *
 * <p>'.' Matches any single character.​​​​ '*' Matches zero or more of the preceding element.
 *
 * <p>The matching should cover the entire input string (not partial).
 *
 * <p>Example 1:
 *
 * <p>Input: s = "aa", p = "a" Output: false Explanation: "a" does not match the entire string "aa".
 *
 * <p>Example 2:
 *
 * <p>Input: s = "aa", p = "a*" Output: true Explanation: '*' means zero or more of the preceding
 * element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 *
 * <p>Example 3:
 *
 * <p>Input: s = "ab", p = ".*" Output: true Explanation: ".*" means "zero or more (*) of any
 * character (.)".
 *
 * <p>Example 4:
 *
 * <p>Input: s = "aab", p = "c*a*b" Output: true Explanation: c can be repeated 0 times, a can be
 * repeated 1 time. Therefore, it matches "aab".
 *
 * <p>Example 5:
 *
 * <p>Input: s = "mississippi", p = "mis*is*p*." Output: false
 */
public class LC_0010_regular_expression_match{
  /*
   * public boolean isMatch(String s, String p) { if (p.length() == 0) return
   * s.length() == 0;
   *
   * if (p.length() == 1 || p.charAt(1) != '*') { if (s.length() < 1 ||
   * (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0))) return false; return
   * isMatch(s.substring(1), p.substring(1)); } else { int i = -1; while (i <
   * s.length() && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
   * if (isMatch(s.substring(i + 1), p.substring(2))) return true; i++; } return
   * false; } }
   */

  enum Status {
    TRUE,
    FALSE
  }

  public boolean isMatch(String s, String p) {
    Status[][] tab = new Status[s.length() + 1][p.length() + 1];
    return dp(s, p, 0, 0, tab);
  }

  private boolean dp(String s, String p, int i, int j, Status[][] tab) {
    if (tab[i][j] != null) return tab[i][j] == Status.TRUE;
    boolean res;
    if (j == p.length()) {
      res = i == s.length();
    } else {
      boolean first_match = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
      if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
        res = (dp(s, p, i, j + 2, tab) || first_match && dp(s, p, i + 1, j, tab));
      } else {
        res = first_match && dp(s, p, i + 1, j + 1, tab);
      }
    }
    tab[i][j] = res ? Status.TRUE : Status.FALSE;
    return res;
  }
}
