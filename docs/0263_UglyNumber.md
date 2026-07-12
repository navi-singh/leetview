# Ugly number

An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return true if n is an ugly number.

## In Plain Terms

Return whether `n` has no prime factors other than `2`, `3`, and `5`. Repeatedly divide out those allowed factors; if the remaining value is `1`, the number is ugly.

Example 1:

> Input: n = 6\
> Output: true\
> Explanation: 6 = 2 × 3

Example 2:

> Input: n = 1\
> Output: true\
> Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.

Example 3:

> Input: n = 14\
> Output: false\
> Explanation: 14 is not ugly since it includes the prime factor 7.

_**Time: O(logn)**_\
&#xNAN;_**Space: O(1)**_

```java
public class LC263_UglyNumber {
  public boolean isUgly(int num) {
    if (num < 1) {
      return false;
    }
    int[] nums = {2, 3, 5};
    for (int i : nums) {
      while (num % i == 0) {
        num /= i;
      }
    }
    return num == 1;
  }
}
```
