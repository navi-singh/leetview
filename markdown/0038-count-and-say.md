---
description: MEDIUM
---

# 38. Count and Say

The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

- `countAndSay(1) = "1"`
- `countAndSay(n)` is the run-length encoding of `countAndSay(n - 1)`

Run-length encoding (RLE) is a string compression method that works by replacing consecutive identical characters with the concatenation of the character and the count of the groupings. For example, to compress the string `"3322251"` we replace `"33"` with `"23"`, replace `"222"` with `"32"`, replace `"5"` with `"15"` and replace `"1"` with `"11"`. So the compressed string becomes `"23321511"`.

Given a positive integer `n`, return the `n`th element of the count-and-say sequence.

**Example 1:**

```text
Input: n = 1
Output: "1"
Explanation: This is the base case.
```

**Example 2:**

```text
Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = say "1"  = one 1              = "11"
countAndSay(3) = say "11" = two 1's            = "21"
countAndSay(4) = say "21" = one 2 + one 1      = "1211"
```

**Constraints:**

- `1 <= n <= 30`

---

## Approach 1: Iterative String Generation

Start from `"1"` and apply the `next` transformation `n - 1` times. The `next` function scans the current string, counting consecutive identical characters using a while-loop, then appends the count followed by the character to a `StringBuilder`.

#### Complexity Analysis

- **Time complexity: O(n * m).** `n` iterations, each scanning a string of length `m` (the length of the previous term, which grows but is bounded in practice for `n <= 30`).
- **Space complexity: O(m).** The `StringBuilder` for the current term is the dominant space usage.

```java
public class LC_0038_CountAndSay {
  public String countAndSay(int n) {

    String res = "1";
    for (int i = 1; i < n; ++i) {
      res = next(res);
    }
    return res;
  }

  private String next(String inp) {

    StringBuilder sb = new StringBuilder();
    for (int index = 0; index < inp.length(); ++index) {
      int count = 1;
      while (index < inp.length() - 1 && inp.charAt(index) == inp.charAt(index + 1)) {
        index++;
        count++;
      }
      sb.append(count);
      sb.append(inp.charAt(index));
    }
    return sb.toString();
  }
}
```

**Key insight:** The inner `while` loop advances `index` in place, so when the outer `for` loop increments it again, processing resumes at the first character of the next run — no separate run-start tracking is needed.
