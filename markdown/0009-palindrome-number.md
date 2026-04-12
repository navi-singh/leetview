---
description: EASY
---

# 9. Palindrome Number

Given an integer `x`, return `true` if `x` is a palindrome, and `false` otherwise.

**Follow up:** Could you solve it without converting the integer to a string?

**Example 1:**

```text
Input: x = 121
Output: true
```

**Example 2:**

```text
Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
```

**Example 3:**

```text
Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
```

**Example 4:**

```text
Input: x = -101
Output: false
```

**Constraints:**

- `-2^31 <= x <= 2^31 - 1`

---

## Approach 1: Reverse and Compare

Negative numbers are immediately `false`. Reverse the integer digit-by-digit (the same technique as problem 7) and compare the reversed value to the original. Single-digit numbers are always palindromes and return `true` immediately.

#### Complexity Analysis

- **Time complexity: O(log x).** The number of digits is `floor(log10(x)) + 1`.
- **Space complexity: O(1).** Only a constant number of variables are used.

```java
public boolean isPalindrome(int x) {
    if (x < 0) return false;
    if (x == 0 || (x / 10 == 0 && x < 10)) return true;
    return reverse(x) == x;
}

private int reverse(int num) {
    int res = 0;
    while (num != 0) {
        res = res * 10 + num % 10;
        num /= 10;
    }
    return res;
}
```

**Key insight:** For non-negative integers that fit in 32 bits, reversing the digits and comparing to the original is equivalent to a string reversal check but avoids any string allocation — if the reversed value overflows it cannot equal `x`, so the comparison still correctly returns `false`.
