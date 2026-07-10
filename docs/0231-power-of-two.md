---
description: EASY
---

# 231. Power of Two

Given an integer `n`, return `true` if it is a power of two. Otherwise, return `false`.

An integer `n` is a power of two if there exists an integer `x` such that `n == 2^x`.

**Example 1:**

```text
Input: n = 1
Output: true
Explanation: 2^0 = 1
```

**Example 2:**

```text
Input: n = 16
Output: true
Explanation: 2^4 = 16
```

**Example 3:**

```text
Input: n = 3
Output: false
```

**Constraints:**

- `-2^31 <= n <= 2^31 - 1`

---

## Approach: Bit Manipulation

A positive integer is a power of two if and only if it has exactly one bit set in its binary representation. The expression `n & (n - 1)` clears the lowest set bit of `n`. If the result is `0`, there was only one bit set, confirming `n` is a power of two. The `n > 0` guard handles the case where `n` is zero or negative.

#### Complexity Analysis

- **Time complexity: O(1).** A single bitwise operation.
- **Space complexity: O(1).** No extra space used.

```java
package com.lc;

public class LC_0231_PowerOfTwo {
  public boolean isPowerOfTwo(int n) {
    return n > 0 && (n & n - 1) == 0;
  }
}
```

**Key insight:** Powers of two have exactly one bit set in binary (e.g., `4 = 100`, `8 = 1000`). Subtracting 1 flips that bit and all lower bits, so `n & (n-1)` is zero if and only if `n` is a power of two.
