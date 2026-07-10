# [50. Pow(x, n)](https://leetcode.com/problems/powx-n/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

Implement <a href="http://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(x, n)</a>, which calculates `x` raised to the power `n` (i.e., `x^n`).

**Example 1:** 

```
Input: x = 2.00000, n = 10
Output: 1024.00000
```

**Example 2:** 

```
Input: x = 2.10000, n = 3
Output: 9.26100
```

**Example 3:** 

```
Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
```

**Constraints:** 

- `-100.0 < x < 100.0`
- `-2^31 <= n <= 2^31-1`
- `n` is an integer.
- Either `x` is not zero or `n > 0`.
- `-10^4 <= x^n <= 10^4`


### Solution
#### Complexity Analysis
**Time complexity: O(logn)**

At each iteration, we reduce n by half, thus it means we will make only logn number of iterations using a while loop.
Thus, it will take overall O(logn) time.
**Space complexity: O(1)**

We don't use any additional space.
```java
class Solution {
    private double binaryExp(double x, long n) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            n = -1 * n;
            x = 1.0 / x;
        }

        double result = 1;
        while (n != 0) {
            // If 'n' is odd we multiply result with 'x' and reduce 'n' by '1'.
            if (n % 2 == 1) {
                result = result * x;
                n -= 1;
            }
            // We square 'x' and reduce 'n' by half, x^n => (x^2)^(n/2).
            x = x * x;
            n = n / 2;
        }
        return result;
    }

    public double myPow(double x, int n) {
        return binaryExp(x, (long) n);
    }
}
```

```python
class Solution:
    def binaryExp(self, x: float, n: int) -> float:
        if n == 0:
            return 1

        # Handle case where, n < 0.
        if n < 0:
            n = -1 * n
            x = 1.0 / x

        # Perform Binary Exponentiation.
        result = 1
        while n != 0:
            # If 'n' is odd we multiply result with 'x' and reduce 'n' by '1'.
            if n % 2 == 1:
                result *= x
                n -= 1
            # We square 'x' and reduce 'n' by half, x^n => (x^2)^(n/2).
            x *= x
            n //= 2
        return result

    def myPow(self, x: float, n: int) -> float:
        return self.binaryExp(x, n)
```
