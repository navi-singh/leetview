package com.lc;

/*
7. Reverse Integer
Easy

Given a 32-bit signed integer, reverse digits of an integer.
Note:
Assume we are dealing with an environment that could only store integers within the 32-bit signed
integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0
when the reversed integer overflows.
Example 1:
Input: x = 123
Output: 321

Example 2:
Input: x = -123
Output: -321

Example 3:
Input: x = 120
Output: 21

Example 4:
Input: x = 0
Output: 0

Constraints:   -231 <= x <= 231 - 1
*/
public class LC_0007_reverse_integer {
  public static int reverse(int x) {
    int res = 0, rem = 0;
    boolean isNegativeNumber = false;
    if (x < 0) {
      isNegativeNumber = true;
      x *= -1;
    }
    while (x != 0) {
      rem = x % 10;
      x /= 10;
      if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) return 0;
      res = res * 10 + rem;
    }
    if (isNegativeNumber) {
      res = res * -1;
    }
    return res;
  }
}
