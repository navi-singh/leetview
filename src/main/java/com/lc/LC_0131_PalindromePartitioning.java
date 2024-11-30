package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0131_PalindromePartitioning {
  /**
   * public List<List<String>> partition(String s) { List<List<String>> res = new
   * ArrayList<List<String>>(); if (s.isEmpty() || s.length() < 1) { return res; } List<String> temp
   * = new ArrayList<String>(); palindromes(s, 0, temp, res); return res; }
   *
   * <p>private void palindromes(String s, int start, List<String> temp, List<List<String>> res) {
   * if (start == s.length()) { res.add(new ArrayList<String>(temp)); return; } for (int end =
   * start; end < s.length(); end++) { if (isPalindrome(s, start, end)) {
   * temp.add(s.substring(start, end + 1)); palindromes(s, end + 1, temp, res);
   * temp.remove(temp.size() - 1); } } }
   *
   * <p>private boolean isPalindrome(String s, int start, int end) { while (start < end) { if
   * (s.charAt(start++) != s.charAt(end--)) { return false; } } return true; }
   */

  /** DP approach */
  public List<List<String>> partition(String s) {
    int len = s.length();
    boolean[][] dp = new boolean[len][len];
    List<List<String>> result = new ArrayList<>();
    dfs(result, s, 0, new ArrayList<>(), dp);
    return result;
  }

  void dfs(
      List<List<String>> result, String s, int start, List<String> currentList, boolean[][] dp) {
    if (start >= s.length()) {
      result.add(new ArrayList<>(currentList));
    }
    for (int end = start; end < s.length(); end++) {
      if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
        dp[start][end] = true;
        currentList.add(s.substring(start, end + 1));
        dfs(result, s, end + 1, currentList, dp);
        currentList.remove(currentList.size() - 1);
      }
    }
  }
}
