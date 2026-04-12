---
description: MEDIUM
---

# 201. Bitwise AND of Numbers Range

Given two integers `left` and `right` that represent the range `[left, right]`, return the bitwise AND of all numbers in this range, inclusive.

**Example 1:**

```text
Input: left = 5, right = 7
Output: 4
Explanation: 5 & 6 & 7 = 4
```

**Example 2:**

```text
Input: left = 0, right = 0
Output: 0
```

**Example 3:**

```text
Input: left = 1, right = 2147483647
Output: 0
```

**Constraints:**

- `0 <= left <= right <= 2^31 - 1`

---

## Approach: Strip Lowest Set Bit

Repeatedly clear the lowest set bit of `n` (using `n & (n - 1)`) until `n <= m`. The result is the common prefix of `m` and `n` in binary, which is exactly the bitwise AND of all integers in the range.

#### Complexity Analysis

- **Time complexity: O(log n).** The number of iterations is bounded by the number of bits in `n`.
- **Space complexity: O(1).** No extra space is used.

```java
public class LC_0201_BitwiseAndRange {
  public int rangeBitwiseAnd(int m, int n) {
    while (n > m) {
      n = n & n - 1;
    }
    return m & n;
  }
}
```

**Key insight:** Any range `[m, n]` where `m < n` will include at least one bit-flip, so all differing lower bits AND to 0; stripping the lowest set bit of `n` until it equals `m` efficiently reveals the shared prefix.
