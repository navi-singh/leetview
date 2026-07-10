---
description: MEDIUM
---

# 166. Fraction to Recurring Decimal

Given two integers representing the `numerator` and `denominator` of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

If multiple answers are possible, return **any of them**.

It is **guaranteed** that the length of the answer string is less than `10^4` for all the given inputs.

**Example 1:**

```text
Input: numerator = 1, denominator = 2
Output: "0.5"
```

**Example 2:**

```text
Input: numerator = 2, denominator = 1
Output: "2"
```

**Example 3:**

```text
Input: numerator = 4, denominator = 333
Output: "0.(012)"
```

**Constraints:**

- `-2^31 <= numerator, denominator <= 2^31 - 1`
- `denominator != 0`

---

## Approach: Long Division with Remainder Tracking

Perform long division manually. Use a HashMap to record the index in the result string where each remainder first appeared. If a remainder is seen again, the digits from that index onward are the repeating part, which gets wrapped in parentheses. Use `long` arithmetic to avoid integer overflow on edge cases like `Integer.MIN_VALUE`.

#### Complexity Analysis

- **Time complexity: O(d).** Where `d` is the denominator — the number of unique remainders is bounded by `d`, so the loop terminates after at most `d` iterations.
- **Space complexity: O(d).** The HashMap stores at most `d` remainder-to-index mappings.

```java
package com.lc;

import java.util.HashMap;
import java.util.Map;

public class LC_0166_FractionToRecurringDecimal {
  public String fractionToDecimal(int numerator, int denominator) {
    if (numerator == 0) {
      return "0";
    }
    if (denominator == 0) {
      return "";
    }
    String res = "";
    if (numerator < 0 ^ denominator < 0) {
      res += "-";
    }
    long num = Math.abs(numerator);
    long den = Math.abs(denominator);
    long div = num / den;
    res += div;
    long rem = (num % den) * 10;
    if (rem == 0) {
      return res;
    }

    Map<Long, Integer> map = new HashMap<>();
    res += ".";
    while (rem != 0) {
      if (map.containsKey(rem)) {
        int beg = map.get(rem);
        String part1 = res.substring(0, beg);
        String part2 = res.substring(beg, res.length());
        return part1 + "(" + part2 + ")";
      }
      map.put(rem, res.length());
      div = rem / den;
      res += String.valueOf(div);
      rem = (rem % den) * 10;
    }
    return res;
  }
}
```

**Key insight:** A remainder seen a second time during long division signals the start of a repeating cycle; recording the string position of each remainder's first occurrence lets us pinpoint exactly where to insert the parentheses.
