package com.lc;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is happy.
 *
 * <p>A happy number is a number defined by the following process:
 *
 * <p>Starting with any positive integer, replace the number by the sum of the squares of its
 * digits. Repeat the process until the number equals 1 (where it will stay), or it loops endlessly
 * in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy.
 *
 * <p>Return true if n is a happy number, and false if not.
 *
 * <p>Example 1:
 *
 * <p>Input: n = 19 Output: true Explanation: 12 + 92 = 82 82 + 22 = 68 62 + 82 = 100 12 + 02 + 02 =
 * 1
 */
public class LC_0202_HappyNumber {
  public boolean isHappy(int n) {
    Set<Integer> dict = new HashSet<>();
    while (!dict.contains(n)) {
      dict.add(n);
      n = transform(n);
      if (n == 1) { // 82 68 100 1
        return true;
      }
    }
    return false;
  }

  private int transform(int n) {
    int result = 0;
    while (n > 0) {
      result += ((n % 10) * (n % 10));
      n = n / 10;
    }
    return result;
  }
}
