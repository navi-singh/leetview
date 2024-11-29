# Ugly number II
An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return the nth ugly number.
 
Example 1:

>Input: n = 10  
Output: 12  
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.

Example 2:
>Input: n = 1  
Output: 1  
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.

***Time: O(n)  
Space: O(n)***
```java
import java.util.ArrayList;
import java.util.List;

public class LC264_UglyNumber2 {
  public int nthUglyNumber(int n) {
    if (n < 1) {
      return 0;
    }
    List<Integer> lis = new ArrayList<Integer>();
    lis.add(1);
    int i = 0, j = 0, k = 0;
    while (lis.size() < n) {
      int m2 = lis.get(i) * 2;
      int m3 = lis.get(j) * 3;
      int m5 = lis.get(k) * 5;
      int min = Math.min(m2, Math.min(m3, m5));
      lis.add(min);
      if (min == m2) {
        i++;
      }
      if (min == m3) {
        j++;
      }
      if (min == m5) {
        k++;
      }
    }
    return lis.get(lis.size() - 1);
  }
}
```