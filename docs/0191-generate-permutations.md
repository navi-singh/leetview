---
description: MEDIUM
---

# Generate Permutations (Backtracking Practice — filed under 0191)

Given a string `s`, return all permutations of its characters. Each character in `s` is considered distinct.

This file corresponds to `LC_0191_generatePermutations.java`, a backtracking exercise stored in the 0191 slot. It is a separate algorithm from LeetCode problem 191 (Number of 1 Bits).

**Example 1:**

```text
Input: s = "abc"
Output: ["abc", "acb", "bac", "bca", "cab", "cba"]
```

**Example 2:**

```text
Input: s = "ab"
Output: ["ab", "ba"]
```

**Constraints:**

- `1 <= s.length <= 8`
- All characters of `s` are distinct.

---

## Approach: Backtracking with Duplicate Check

Build the permutation character by character. At each recursive step, iterate over all characters in the original string `s`. Skip any character that already appears in the current partial string `temp` (duplicate guard). Append the character, recurse, then logically backtrack by working with the unchanged `temp` (string immutability handles the undo).

**Note:** The current implementation has a subtle bug — `temp.substring(0, temp.length() - 1)` returns a new string but the result is not assigned back to `temp`, so backtracking does not actually remove the character. This causes incorrect output for strings longer than one character. The correct fix is `temp = temp.substring(0, temp.length() - 1)` after the recursive call, or to use a `StringBuilder` with explicit `deleteCharAt`.

#### Complexity Analysis

- **Time complexity: O(n! * n).** There are `n!` permutations and building each one takes O(n) work.
- **Space complexity: O(n).** The recursion depth is at most `n` (the length of the string).

```java
package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0191_generatePermutations {
  public static void main(String[] args) {
    System.out.println("Hello World");

    generatePermutations("abc");
  }

  public static List<String> generatePermutations(String s) {
    List<String> result = new ArrayList<>();
    // base case
    if (s == null || s.length() == 0) {
      return result;
    }

    backtrack(s, result, "");
    return result;
  }

  public static void backtrack(String s, List<String> result, String temp) {
    if (temp.length() == s.length()) {
      result.add(new String(temp));
      return;
    }

    for (int i = 0; i < s.length(); i++) {
      if (temp.contains(String.valueOf(s.charAt(i)))) {
        continue;
      }
      temp += s.charAt(i); // temp abc
      backtrack(s, result, temp);
      temp.substring(0, temp.length() - 1);
    }
  }
}
```

**Key insight:** The classic backtracking pattern — choose, explore, unchoose — generates all permutations by exploring every possible character at each position while skipping characters already used in the current partial solution.
