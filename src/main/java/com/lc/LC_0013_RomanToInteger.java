package com.lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Example 1:
 *
 * <p>Input: s = "III" Output: 3
 *
 * <p>Example 2:
 *
 * <p>Input: s = "IV" Output: 4
 *
 * <p>Example 3:
 *
 * <p>Input: s = "IX" Output: 9
 *
 * <p>Example 4:
 *
 * <p>Input: s = "LVIII" Output: 58 Explanation: L = 50, V= 5, III = 3.
 *
 * <p>Example 5:
 *
 * <p>Input: s = "MCMXCIV" Output: 1994 Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class LC_0013_RomanToInteger{
  public static void main(String[] args) {
    LC_0013_RomanToInteger s = new LC_0013_RomanToInteger();
    System.out.println(s.romanToInt("IXV"));
  }

  public int romanToInt(String s) {
    int res = 0;
    Map<Character, Integer> map =
        new HashMap<Character, Integer>() {
          {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
          }
        };
    for (int index = 0; index < s.length(); index++) {
      int temp = map.get(s.charAt(index));
      if (index < s.length() - 1 && temp < map.get(s.charAt(index + 1))) res -= temp;
      else res += temp;
    }
    return res;
  }
}
