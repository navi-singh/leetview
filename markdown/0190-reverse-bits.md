---
description: EASY
---

# 190. Reverse Bits

Reverse bits of a given 32 bits unsigned integer.

**Note:**

- Note that in some languages, such as Java, there is no unsigned integer type. In this case, both input and output will be given as a signed integer type. They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
- In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in **Example 2** above, the input represents the signed integer `-3` and the output represents the signed integer `-1073741825`.

**Example 1:**

```text
Input: n = 00000010100101000001111010011100
Output:    964176192 (00111001011110000010100101000000)
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
```

**Example 2:**

```text
Input: n = 11111111111111111111111111111101
Output:   3221225471 (10111111111111111111111111111111)
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10111111111111111111111111111111.
```

**Constraints:**

- The input must be a **binary string** of length `32`.

**Follow-up:** If this function is called many times, how would you optimize it?

---

## Approach: Iterative Bit Swapping

Iterate over the first 16 bit positions (0 through 15). For each position `i`, swap bit `i` with its mirror bit at position `31 - i`. Extract both bits with a right-shift and AND with 1; if they differ, XOR the integer with a mask that has both positions set to flip them simultaneously.

#### Complexity Analysis

- **Time complexity: O(1).** Exactly 16 swap iterations are performed regardless of input — the word size is fixed at 32 bits.
- **Space complexity: O(1).** All operations are performed on the integer in place.

```java
package com.lc;

public class LC_0190_Reverse_bits {
  public int reverseBits(int n) {
    for (int i = 0; i < 16; i++) {
      n = swapBits(n, i, 32 - i - 1);
    }
    return n;
  }

  private int swapBits(int n, int start, int end) {
    int a = (n >> start) & 1;
    int b = (n >> end) & 1;
    if ((a ^ b) != 0) {
      n ^= (1 << start) | (1 << end);
    }
    return n;
  }
}
```

**Key insight:** Two bits need to be swapped only when they differ — when they are the same, XORing their positions is a no-op — so the `if ((a ^ b) != 0)` guard avoids unnecessary work and keeps the logic clean.
