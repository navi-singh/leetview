---
description: MEDIUM
---

# 91. Decode Ways

A message containing letters from `A-Z` can be encoded into numbers using the following mapping:

```
'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
```

To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above. There may be multiple ways to decode a message.

Given a string `s` containing only digits, return the number of ways to decode it. If there are no valid decodings, return `0`.

**Example 1:**

```text
Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
```

**Example 2:**

```text
Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
```

**Example 3:**

```text
Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero.
```

**Constraints:**

- `1 <= s.length <= 100`
- `s` contains only digits and may contain leading zeros

---

## Approach 1: Dynamic Programming

A 1D DP array of size `n+1` is used where `dp[i]` represents the number of ways to decode the first `i` characters of `s`. The base cases are `dp[0] = 1` (empty string) and `dp[1] = 1` (single non-zero digit). For each position `i` from 1 to `n-1`, two transitions are considered:
- If the current character `s[i]` is non-zero, then `dp[i+1] += dp[i]` (decode `s[i]` as a single digit).
- If the two-character number formed by `s[i-1]` and `s[i]` is between 10 and 26, then `dp[i+1] += dp[i-1]` (decode the pair as a single letter).

#### Complexity Analysis

- **Time complexity: O(n).** A single pass over the string fills the DP table.
- **Space complexity: O(n).** The DP array has `n+1` entries.

```java
public int numDecodings(String s) {
    if (s.isEmpty() || s.charAt(0) < '1' || s.charAt(0) > '9') {
        return 0;
    }
    int[] dp = new int[s.length() + 1];
    dp[0] = dp[1] = 1;
    for (int i = 1; i < s.length(); i++) {
        int maxVal = (s.charAt(i - 1) - '0') * 10 + s.charAt(i) - '0';
        if (maxVal <= 26 && maxVal > 9) {
            dp[i + 1] += dp[i - 1];
        }
        if (s.charAt(i) != '0') {
            dp[i + 1] += dp[i];
        }
    }
    return dp[s.length()];
}
```

**Key insight:** Each position can be decoded either alone (if non-zero) or together with the preceding digit (if the pair is between 10 and 26), making this a Fibonacci-style DP where each state depends on at most the two preceding states.
