package com.lc;

public class LC_0029_DivideIntegers {
  // Below solution won't work for Integer.MIN_VALUE
  public int divideNotWork(int dividend, int divisor) {
    if (divisor == 0) return Integer.MAX_VALUE;

    int res = 0;
    boolean isDividendNeg = dividend < 0;
    boolean isDivisorNeg = divisor < 0;
    int lDivisor = Math.abs(divisor);
    int lDividend = Math.abs(dividend);
    while (lDividend >= lDivisor) {
      lDividend -= lDivisor;
      res++;
    }
    if ((isDividendNeg && !isDivisorNeg) || (!isDividendNeg && isDivisorNeg)) res = -res;
    return res;
  }

  public int divide(int dividend, int divisor) {
    if (dividend == 0) return 0;
    if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
    boolean sign = (divisor > 0) ^ (dividend > 0);
    long a = Math.abs((long) dividend);
    long b = Math.abs((long) divisor);
    int ans = 0;
    while (a >= b) {
      int shift = 0;
      while (a >= (b << shift)) {
        shift++;
      }
      ans += (1 << (shift - 1));
      a -= b << (shift - 1);
    }
    if (sign) return -ans;
    return ans;
  }
}
