package com.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * Example 1:
 *
 * <p>Input: digits = "23" Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * <p>Example 2:
 *
 * <p>Input: digits = "" Output: []
 *
 * <p>Example 3:
 *
 * <p>Input: digits = "2" Output: ["a","b","c"]
 */
public class LC_0017_LetterCombinations{
  public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<>();
    if (digits == null || digits.isEmpty()) return res;
    String[] digitToAlpha = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    res.add("");
    for (char digit : digits.toCharArray()) {
      List<String> lis = new ArrayList<String>();
      String letters = digitToAlpha[digit - '0'];
      for (char c : letters.toCharArray()) {
        for (String temp : res) {
          lis.add(temp + c);
        }
      }
      res = lis;
    }
    return res;
  }
}
