package com.lc;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * <p>Example 1:
 *
 * <p>Input: s = "babad" Output: "bab" Note: "aba" is also a valid answer.
 *
 * <p>Example 2:
 *
 * <p>Input: s = "cbbd" Output: "bb"
 *
 * <p>Example 3:
 *
 * <p>Input: s = "a" Output: "a"
 *
 * <p>Example 4:
 *
 * <p>Input: s = "ac" Output: "a"
 */
public class LC_0005_longestPalindrome{
  public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    int strLen = s.length();
    for (int i = 0; i < strLen; i++) {
      int evenLen = getLength(s, i, i + 1);
      int oddLen = getLength(s, i, i);
      int len = Math.max(oddLen, evenLen);
      if ((end - start) < len) {
        start = i - (len - 1) / 2;
        end = i + len / 2;
      }
    }
    return s.substring(start, end + 1);
  }

  private int getLength(String s, int low, int high) {
    while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
      low--;
      high++;
    }
    return high - low - 1;
  }
}
