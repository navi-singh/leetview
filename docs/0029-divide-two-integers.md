---
description: MEDIUM
---

# 29. Divide Two Integers

Given two integers `dividend` and `divisor`, divide two integers without using multiplication, division, or mod operator. Return the quotient after dividing `dividend` by `divisor`.

The integer division should truncate toward zero, which means losing its fractional part. For example, `truncate(8.345) = 8` and `truncate(-2.7335) = -2`.

**Note:** Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: `[−2^31, 2^31 − 1]`. For this problem, if the quotient is strictly greater than `2^31 − 1`, then return `2^31 − 1`, and if the quotient is strictly less than `-2^31`, then return `-2^31`.

**Example 1:**

```text
Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = 3.33333.. which is truncated to 3.
```

**Example 2:**

```text
Input: dividend = 7, divisor = -2
Output: -3
Explanation: 7/-2 = -3.5 which is truncated to -3.
```

**Constraints:**

- `-2^31 <= dividend, divisor <= 2^31 - 1`
- `divisor != 0`

---

## Approach 1: Bit Shifting (Exponential Search)

Convert both numbers to their absolute values as `long` to avoid overflow. For each iteration, find the largest shift such that `divisor << shift <= dividend`. Add `1 << (shift - 1)` to the answer and subtract `divisor << (shift - 1)` from the remaining dividend. Repeat until the dividend is less than the divisor. Apply the sign at the end and clamp for the `MIN_VALUE / -1` overflow case.

#### Complexity Analysis

- **Time complexity: O(log^2 n).** The outer loop runs O(log n) times (the dividend halves roughly each iteration), and the inner shift search is also O(log n).
- **Space complexity: O(1).** Only a fixed number of variables are used.

```java
public class LC_0029_DivideIntegers {
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
```

**Key insight:** By doubling the divisor via bit shifts, we can subtract large multiples at once (similar to long division in binary), reducing O(n) subtractions to O(log^2 n). Casting to `long` before `Math.abs` is essential to handle `Integer.MIN_VALUE` without overflow.

---

## Approach 2: Naive Subtraction (Does Not Handle MIN_VALUE)

Repeatedly subtract the divisor from the dividend and count iterations. Simple but O(n) and breaks for `Integer.MIN_VALUE` because `Math.abs(Integer.MIN_VALUE)` overflows.

#### Complexity Analysis

- **Time complexity: O(n).** Up to `dividend / divisor` subtractions.
- **Space complexity: O(1).**

```java
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
```

**Key insight:** The naive approach illustrates the core idea but fails for `Integer.MIN_VALUE` because `int` cannot represent its absolute value. The bit-shift approach fixes this by working with `long`.
