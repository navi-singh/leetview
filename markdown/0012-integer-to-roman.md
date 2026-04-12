---
description: MEDIUM
---

# 12. Integer to Roman

Roman numerals are represented by seven different symbols: `I`, `V`, `X`, `L`, `C`, `D` and `M`.

| Symbol | Value |
|--------|-------|
| I      | 1     |
| V      | 5     |
| X      | 10    |
| L      | 50    |
| C      | 100   |
| D      | 500   |
| M      | 1000  |

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not `IIII`. Instead, the number four is written as `IV`. There are six instances where subtraction is used:

- `I` can be placed before `V` (5) and `X` (10) to make 4 and 9.
- `X` can be placed before `L` (50) and `C` (100) to make 40 and 90.
- `C` can be placed before `D` (500) and `M` (1000) to make 400 and 900.

Given an integer, convert it to a roman numeral.

**Example 1:**

```text
Input: num = 3
Output: "III"
```

**Example 2:**

```text
Input: num = 4
Output: "IV"
```

**Example 3:**

```text
Input: num = 9
Output: "IX"
```

**Example 4:**

```text
Input: num = 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.
```

**Example 5:**

```text
Input: num = 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
```

**Constraints:**

- `1 <= num <= 3999`

---

## Approach 1: Greedy with Value-Symbol Table

Maintain two parallel arrays: one with integer values in descending order (including the six subtraction cases like 900, 400, etc.) and one with the corresponding Roman symbol strings. For each value, repeatedly subtract it from `num` and append the symbol to the result as long as `num >= value`. This greedy approach works because the values are ordered so the largest possible symbol is always chosen first.

#### Complexity Analysis

- **Time complexity: O(1).** The input is bounded by 3999, so the number of iterations is fixed and constant.
- **Space complexity: O(1).** The output string length is bounded (at most 15 characters for 3888 = "MMMDCCCLXXXVIII").

```java
public String intToRoman(int num) {
    StringBuilder res = new StringBuilder();
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    for (int i = 0; i < values.length; i++) {
        while (num >= values[i]) {
            num -= values[i];
            res.append(romans[i]);
        }
    }
    return res.toString();
}
```

**Key insight:** Including the six subtractive combinations (CM, CD, XC, XL, IX, IV) directly in the value table makes the greedy algorithm handle all edge cases uniformly — no special-casing is needed for the subtraction rule.
