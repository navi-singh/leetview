---
description: EASY
---

# 67. Add Binary

Given two binary strings `a` and `b`, return their sum as a binary string.

**Example 1:**

```text
Input: a = "11", b = "1"
Output: "100"
```

**Example 2:**

```text
Input: a = "1010", b = "1011"
Output: "10101"
```

**Constraints:**

- `1 <= a.length, b.length <= 10^4`
- `a` and `b` consist only of `'0'` or `'1'` characters.
- Each string does not contain leading zeros except for the zero itself.

---

## Approach 1: Bit-by-Bit Addition with Carry

Use two pointers starting from the least-significant end of each string. At each step compute `sum = carry + bit_a + bit_b`. The current result bit is `sum % 2` and the new carry is `sum / 2` (i.e., `sum >= 2`). Prepend each computed bit to a `StringBuilder`. After the loop, prepend a `'1'` if a carry remains.

#### Complexity Analysis

- **Time complexity: O(max(m, n)).** We process each bit position of both strings once, where `m` and `n` are the lengths of `a` and `b`.
- **Space complexity: O(max(m, n)).** The output string is at most one character longer than the longer input.

```java
public class LC_0067_AddBinary {
  public String addBinary(String a, String b) {
    StringBuilder sb = new StringBuilder();
    int i = a.length() - 1, j = b.length() - 1;
    int sum = 0, carry = 0;
    char one = '1', zero = '0';
    while (i >= 0 || j >= 0) {
      sum = carry;
      if (i >= 0 && a.charAt(i) == '1') {
        sum++;
      }
      if (j >= 0 && b.charAt(j) == '1') {
        sum++;
      }
      if (sum >= 2) {
        carry = 1;
      } else {
        carry = 0;
      }
      sb.insert(0, (char) (sum % 2 + zero));
      i--;
      j--;
    }
    if (carry == 1) {
      sb.insert(0, one);
    }
    return sb.toString();
  }
}
```

**Key insight:** Binary addition mirrors decimal addition — carry is 1 whenever the column sum is 2 or 3, and the result bit is the column sum modulo 2.
