package com.lc;

/**
 * Example 1:
 *
 * <p>Input: n = 1 Output: "1" Explanation: This is the base case.
 *
 * <p>Example 2:
 *
 * <p>Input: n = 4 Output: "1211" Explanation: countAndSay(1) = "1" countAndSay(2) = say "1" = one 1
 * = "11" countAndSay(3) = say "11" = two 1's = "21" countAndSay(4) = say "21" = one 2 + one 1 =
 * "12" + "11" = "1211"
 */
public class LC_0038_CountAndSay{
  public String countAndSay(int n) {

    String res = "1";
    for (int i = 1; i < n; ++i) {
      res = next(res);
    }
    return res;
  }

  private String next(String inp) {

    StringBuilder sb = new StringBuilder();
    for (int index = 0; index < inp.length(); ++index) {
      int count = 1;
      while (index < inp.length() - 1 && inp.charAt(index) == inp.charAt(index + 1)) {
        index++;
        count++;
      }
      sb.append(count);
      sb.append(inp.charAt(index));
    }
    return sb.toString();
  }
}
