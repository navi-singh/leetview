---
description: MEDIUM
---

# 43. Multiply Strings

Given two non-negative integers `num1` and `num2` represented as strings, return the product of `num1` and `num2`, also represented as a string.

**Note:** You must not use any built-in BigInteger library or convert the inputs to integer directly.

**Example 1:**

```text
Input: num1 = "2", num2 = "3"
Output: "6"
```

**Example 2:**

```text
Input: num1 = "123", num2 = "456"
Output: "56088"
```

**Constraints:**

- `1 <= num1.length, num2.length <= 200`
- `num1` and `num2` consist of digits only.
- Both `num1` and `num2` do not contain any leading zero, except the number `0` itself.

---

## Approach 1: Grade-School Multiplication with Carry Array

Both strings are reversed so that digit positions correspond to their numeric place values (index 0 = ones place). A result character array of size `m + n` is pre-filled with `'0'`. For each digit pair `(i, j)`, the partial product is accumulated at position `i + j` in the result array, and any carry propagates to `i + j + 1`. After all multiplications, the result array is reversed and leading zeros are stripped.

#### Complexity Analysis

- **Time complexity: O(m * n).** Where `m` and `n` are the lengths of `num1` and `num2`. Every digit of `num1` is multiplied against every digit of `num2`.
- **Space complexity: O(m + n).** The result array has at most `m + n` digits.

```java
public class LC_0043_MulitplyStrings {
  private final char zero = '0';

  public String multiply(String num1, String num2) {
    String zeroStr = "0";
    if (num1.equals(zeroStr) || num2.equals(zeroStr)) {
      System.out.println("= World");
      return zeroStr;
    }
    String n1 = new StringBuilder(num1).reverse().toString();
    String n2 = new StringBuilder(num2).reverse().toString();
    char[] res = new char[num1.length() + num2.length()];
    Arrays.fill(res, '0');
    for (int i = 0; i < n1.length(); ++i) {
      int carry = 0;
      int mul = charToInt(n1.charAt(i));
      int j = 0;
      for (; j < n2.length(); ++j) {
        carry += charToInt(n2.charAt(j)) * mul + charToInt(res[i + j]);
        res[i + j] = (char) (carry % 10 + zero);
        carry /= 10;
      }
      if (carry > 0) {
        res[i + j] = (char) ((charToInt(res[i + j]) + carry) + zero);
      }
    }
    StringBuilder sb = new StringBuilder().append(res);
    sb.reverse();
    int index = 0;
    while (index < sb.length() && sb.charAt(index) == '0') {
      index++;
    }
    return String.valueOf(sb.substring(index));
  }

  private int charToInt(char c) {
    return c - zero;
  }
}
```

**Key insight:** Working with reversed strings allows carry propagation to naturally flow left-to-right through the result array, mirroring how grade-school multiplication accumulates partial products into a single buffer.
