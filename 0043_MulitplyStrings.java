import java.util.Arrays;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1
 * and num2, also represented as a string.
 *
 * <p>Note: You must not use any built-in BigInteger library or convert the inputs to integer
 * directly.
 *
 * <p>Example 1:
 *
 * <p>Input: num1 = "2", num2 = "3" Output: "6"
 *
 * <p>Example 2:
 *
 * <p>Input: num1 = "123", num2 = "456" Output: "56088"
 */
class Solution {
  private final char zero = '0';

  public String multiply(String num1, String num2) {
    String zeroStr = "0";
    if (num1.equals(zeroStr) || num2.equals(zeroStr)) {
      System.out.println("= World");
      return zeroStr;
    }
    String n1 = new StringBuilder(num1).reverse().toString();
    String n2 = new StringBuilder(num2).reverse().toString();
    char[] res = new char[num1.length() + num2.length()];
    Arrays.fill(res, '0');
    for (int i = 0; i < n1.length(); ++i) {
      int carry = 0;
      int mul = charToInt(n1.charAt(i));
      int j = 0;
      for (; j < n2.length(); ++j) {
        carry += charToInt(n2.charAt(j)) * mul + charToInt(res[i + j]);
        res[i + j] = (char) (carry % 10 + zero);
        carry /= 10;
      }
      if (carry > 0) {
        res[i + j] = (char) ((charToInt(res[i + j]) + carry) + zero);
      }
    }
    StringBuilder sb = new StringBuilder().append(res);
    sb.reverse();
    int index = 0;
    while (index < sb.length() && sb.charAt(index) == '0') {
      index++;
    }
    return String.valueOf(sb.substring(index));
  }

  private int charToInt(char c) {
    return c - zero;
  }
}
