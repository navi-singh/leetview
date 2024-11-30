package com.lc;

/**
 * Example 1:
 *
 * <p>Input: num = 3 Output: "III"
 *
 * <p>Example 2:
 *
 * <p>Input: num = 4 Output: "IV"
 *
 * <p>Example 3:
 *
 * <p>Input: num = 9 Output: "IX"
 *
 * <p>Example 4:
 *
 * <p>Input: num = 58 Output: "LVIII" Explanation: L = 50, V = 5, III = 3.
 */
public class LC_0012_IntegerToRoman {
  public String intToRoman(int num) {
    StringBuilder res = new StringBuilder();
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    for (int i = 0; i < values.length; i++) {
      while (num >= values[i]) {
        num -= values[i];
        res.append(romans[i]);
      }
    }
    return res.toString();
  }
}
