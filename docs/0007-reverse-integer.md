---
description: MEDIUM
---

# 7. Reverse Integer

Given a signed 32-bit integer `x`, return `x` with its digits reversed. If reversing `x` causes the value to go outside the signed 32-bit integer range `[-2^31, 2^31 - 1]`, then return `0`.

**Assume the environment does not allow you to store 64-bit integers (signed or unsigned).**

**Example 1:**

```text
Input: x = 123
Output: 321
```

**Example 2:**

```text
Input: x = -123
Output: -321
```

**Example 3:**

```text
Input: x = 120
Output: 21
```

**Example 4:**

```text
Input: x = 0
Output: 0
```

**Constraints:**

- `-2^31 <= x <= 2^31 - 1`

---

## Approach 1: Pop and Push Digits with Overflow Check

Extract digits one at a time from the end of the number using modulo, build the reversed result by multiplying the running total by 10 and adding the digit. Before each multiplication, check whether the result would overflow 32-bit bounds by comparing against `Integer.MAX_VALUE / 10`.

#### Complexity Analysis

- **Time complexity: O(log x).** The number of digits in `x` is `floor(log10(x)) + 1`.
- **Space complexity: O(1).** Only a constant number of variables are used.

```java
public static int reverse(int x) {
    int res = 0, rem = 0;
    boolean isNegativeNumber = false;
    if (x < 0) {
        isNegativeNumber = true;
        x *= -1;
    }
    while (x != 0) {
        rem = x % 10;
        x /= 10;
        if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) return 0;
        res = res * 10 + rem;
    }
    if (isNegativeNumber) {
        res = res * -1;
    }
    return res;
}
```

**Key insight:** Checking `res > Integer.MAX_VALUE / 10` before multiplying (rather than after) avoids the actual overflow, since the multiplication itself could silently wrap around in a 32-bit environment.
