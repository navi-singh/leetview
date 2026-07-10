---
description: MEDIUM
---

# 8. String to Integer (atoi)

Implement the `myAtoi(string s)` function, which converts a string to a 32-bit signed integer (similar to C/C++'s `atoi` function).

The algorithm for `myAtoi(string s)` is as follows:

1. Read in and ignore any leading whitespace.
2. Check if the next character is `'-'` or `'+'`. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
3. Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
4. Convert these digits into an integer (i.e. `"123" -> 123`, `"0032" -> 32`). If no digits were read, then the integer is `0`. Change the sign as necessary (from step 2).
5. If the integer is out of the 32-bit signed integer range `[-2^31, 2^31 - 1]`, then clamp the integer so that it remains in the range. Specifically, integers less than `-2^31` should be clamped to `-2^31`, and integers greater than `2^31 - 1` should be clamped to `2^31 - 1`.
6. Return the integer as the final result.

**Example 1:**

```text
Input: s = "42"
Output: 42
```

**Example 2:**

```text
Input: s = "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign. Then take as many numerical digits as possible, which gets 42.
```

**Example 3:**

```text
Input: s = "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
```

**Example 4:**

```text
Input: s = "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical digit or a +/- sign. Therefore no valid conversion could be performed.
```

**Example 5:**

```text
Input: s = "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer. Therefore INT_MIN (-2^31) is returned.
```

**Constraints:**

- `0 <= s.length <= 200`
- `s` consists of English letters (lower-case and upper-case), digits, `' '`, `'+'`, `'-'` and `'.'`.

---

## Approach 1: Linear Scan with Overflow Guard

Step through the string with a single index pointer. First skip leading spaces, then detect an optional sign character, then consume consecutive digit characters while building the result. Before each digit is appended, check whether the current result would overflow `Integer.MAX_VALUE` (2147483647) — if so, return the appropriate clamp value immediately.

#### Complexity Analysis

- **Time complexity: O(n).** The string is scanned at most once from left to right.
- **Space complexity: O(1).** Only a constant number of index and result variables are used.

```java
public int myAtoi(String str) {
    if (str == null || str.isEmpty()) return 0;
    boolean isNegative = false;
    int index = 0, res = 0;
    while (index < str.length() && str.charAt(index) == ' ') {
        index++;
    }
    if (str.length() == index) {
        return 0;
    }
    if (str.charAt(index) == '-') {
        index++;
        isNegative = true;
    } else if (str.charAt(index) == '+') index++;
    while (index < str.length() && str.charAt(index) >= '0' && str.charAt(index) <= '9') {
        if (res > Integer.MAX_VALUE / 10
            || (res == Integer.MAX_VALUE / 10 && str.charAt(index) - '0' > 7)) {
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        res = res * 10 + str.charAt(index) - '0';
        index++;
    }
    return isNegative ? -res : res;
}
```

**Key insight:** The overflow guard checks `res > MAX / 10` OR (`res == MAX / 10` AND the next digit exceeds 7), which precisely catches both the tens-place and units-place overflow without needing a 64-bit intermediate.
