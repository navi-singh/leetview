---
description: MEDIUM
---

# 172. Factorial Trailing Zeroes

Given an integer `n`, return the number of trailing zeroes in `n!`.

Note that `n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1`.

**Example 1:**

```text
Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.
```

**Example 2:**

```text
Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.
```

**Example 3:**

```text
Input: n = 0
Output: 0
```

**Constraints:**

- `0 <= n <= 10^4`

**Follow-up:** Could you write a solution that works in logarithmic time complexity?

---

## Approach: Count Factors of 5

Trailing zeroes in `n!` are produced by factors of 10 = 2 × 5. Since factors of 2 are always more plentiful than factors of 5, we only need to count how many times 5 divides into the numbers from 1 to `n`. This is done by summing `⌊n/5⌋ + ⌊n/25⌋ + ⌊n/125⌋ + ...` until the power of 5 exceeds `n`.

#### Complexity Analysis

- **Time complexity: O(log_5 n).** The loop iterates once for each power of 5 up to `n`.
- **Space complexity: O(1).** Only a result counter is maintained.

```java
package com.lc;

public class LC_0172_FactorialTrailingZeroes {
  public int trailingZeroes(int n) {
    int res = 0;
    for (int i = 5; n / i >= 1; i = i * 5) {
      res += n / i;
    }
    return res;
  }
}
```

**Key insight:** Numbers that are multiples of 25 contribute two factors of 5 (e.g., 25 = 5×5), multiples of 125 contribute three, and so on — summing `n` divided by each power of 5 correctly accounts for all of them.
