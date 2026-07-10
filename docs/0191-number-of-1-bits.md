---
description: EASY
---

# 191. Number of 1 Bits

Write a function that takes the binary representation of a positive integer and returns the number of set bits it has (also known as the Hamming weight).

**Example 1:**

```text
Input: n = 11
Output: 3
Explanation: The input binary string 1011 has a total of three set bits.
```

**Example 2:**

```text
Input: n = 128
Output: 1
Explanation: The input binary string 10000000 has a total of one set bit.
```

**Example 3:**

```text
Input: n = 2147483645
Output: 30
Explanation: The input binary string 1111111111111111111111111111101 has a total of thirty set bits.
```

**Constraints:**

- `1 <= n <= 2^31 - 1`

**Follow-up:** If this function is called many times, how would you optimize it?

---

## Approach: Bit Mask Iteration

Iterate through all 32 bit positions. At each position `i`, check whether the corresponding bit is set by ANDing `n` with a mask `(1 << i)`. If the result is non-zero, the bit is set and the count is incremented.

#### Complexity Analysis

- **Time complexity: O(1).** Exactly 32 iterations are performed regardless of the value of `n`.
- **Space complexity: O(1).** Only a single counter variable is used.

```java
package com.lc;

public class LC_0191_NumberOf1Bits {
  public int hammingWeight(int n) {
    int count = 0;
    for (int i = 0; i < 32; i++) {
      if ((n & (1 << i)) != 0) {
        {
          count++;
        }
      }
    }
    return count;
  }
}
```

**Key insight:** Shifting a 1-bit mask left by `i` isolates exactly the `i`th bit position, making it straightforward to check every bit in a fixed 32-iteration loop without modifying `n`.
