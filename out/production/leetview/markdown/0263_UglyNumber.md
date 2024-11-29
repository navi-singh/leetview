# Ugly number
An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return true if n is an ugly number.

Example 1:
>Input: n = 6  
Output: true  
Explanation: 6 = 2 × 3

Example 2:
>Input: n = 1  
Output: true  
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.

Example 3:
>Input: n = 14  
Output: false  
Explanation: 14 is not ugly since it includes the prime factor 7.

***Time: O(logn)  
Space: O(1)***
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