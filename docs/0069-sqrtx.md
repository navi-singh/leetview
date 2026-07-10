---
description: EASY
---

# 69. Sqrt(x)

Given a non-negative integer `x`, return the square root of `x` rounded down to the nearest integer. The returned integer should be **non-negative** as well.

You must not use any built-in exponent function or operator, such as `pow(x, 0.5)` or `x ** 0.5`.

**Example 1:**

```text
Input: x = 4
Output: 2
```

**Example 2:**

```text
Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.828..., and since we round down, 2 is returned.
```

**Constraints:**

- `0 <= x <= 2^31 - 1`

---

## Approach 1: Binary Search

Handle base cases `x < 1` (return 0) and `x < 4` (return 1) directly. Then binary search the range `[2, x/2]`. To avoid integer overflow when computing `mid * mid`, compare using integer division: if `x / mid == mid` the answer is exact; if `x / mid > mid` the answer is larger; otherwise shrink right. When the loop ends, `right` holds the floor of the square root.

#### Complexity Analysis

- **Time complexity: O(log x).** The search space is halved at each step.
- **Space complexity: O(1).** Only a few integer variables are used.

```java
public class LC_0069_Sqrt {
  public int mySqrt(int x) {
    if (x < 1) {
      return 0;
    }
    if (x < 4) {
      return 1;
    }
    int left = 2, right = x / 2;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      int temp = x / mid;
      if (temp == mid) {
        return mid;
      } else if (temp > mid) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return right;
  }
}
```

**Key insight:** Using `x / mid` instead of `mid * mid` sidesteps integer overflow for large values of `x`, and `right` naturally lands on the floor of the square root when the loop exits.
