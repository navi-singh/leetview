---
description: EASY
---

# 13. Roman to Integer

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

Roman numerals are usually written largest to smallest from left to right. However, there are six exceptions where a smaller value precedes a larger value indicating subtraction (e.g. `IV` = 4, `IX` = 9, `XL` = 40, `XC` = 90, `CD` = 400, `CM` = 900).

Given a roman numeral, convert it to an integer.

**Example 1:**

```text
Input: s = "III"
Output: 3
```

**Example 2:**

```text
Input: s = "IV"
Output: 4
```

**Example 3:**

```text
Input: s = "IX"
Output: 9
```

**Example 4:**

```text
Input: s = "LVIII"
Output: 58
Explanation: L = 50, V = 5, III = 3.
```

**Example 5:**

```text
Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
```

**Constraints:**

- `1 <= s.length <= 15`
- `s` contains only the characters `('I', 'V', 'X', 'L', 'C', 'D', 'M')`.
- It is guaranteed that `s` is a valid roman numeral in the range `[1, 3999]`.

---

## Approach 1: Left-to-Right Scan with Lookahead

Use a HashMap to map each Roman character to its integer value. Iterate through the string left to right. For each character, peek at the next character: if the current value is less than the next value, it is a subtraction case — subtract the current value. Otherwise, add it. This single pass correctly handles all six subtractive combinations.

#### Complexity Analysis

- **Time complexity: O(1).** The input length is bounded by 15 characters (valid Roman numerals up to 3999).
- **Space complexity: O(1).** The HashMap has a fixed size of 7 entries.

```java
public int romanToInt(String s) {
    int res = 0;
    Map<Character, Integer> map =
        new HashMap<Character, Integer>() {
          {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
          }
        };
    for (int index = 0; index < s.length(); index++) {
        int temp = map.get(s.charAt(index));
        if (index < s.length() - 1 && temp < map.get(s.charAt(index + 1))) res -= temp;
        else res += temp;
    }
    return res;
}
```

**Key insight:** The rule "subtract if the current symbol is smaller than the next one" elegantly covers all six subtraction cases without needing to check for specific two-character combinations — a single value comparison is sufficient.
