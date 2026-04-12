---
description: MEDIUM
---

# 17. Letter Combinations of a Phone Number

Given a string containing digits from `2-9` inclusive, return all possible letter combinations that the number could represent. Return the answer in **any order**.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

```
2 -> abc
3 -> def
4 -> ghi
5 -> jkl
6 -> mno
7 -> pqrs
8 -> tuv
9 -> wxyz
```

**Example 1:**

```text
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
```

**Example 2:**

```text
Input: digits = ""
Output: []
```

**Example 3:**

```text
Input: digits = "2"
Output: ["a","b","c"]
```

**Constraints:**

- `0 <= digits.length <= 4`
- `digits[i]` is a digit in the range `['2', '9']`.

---

## Approach 1: Iterative Expansion (BFS-style)

Start with a list containing one empty string. For each digit in the input, expand every current partial combination by appending each letter that the digit maps to. Replace the current list with the expanded list. After processing all digits, the list contains all complete combinations.

#### Complexity Analysis

- **Time complexity: O(4^n * n).** In the worst case (all digits map to 4 letters), there are `4^n` combinations each of length `n`.
- **Space complexity: O(4^n * n).** The result list holds all combinations.

```java
public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<>();
    if (digits == null || digits.isEmpty()) return res;
    String[] digitToAlpha = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    res.add("");
    for (char digit : digits.toCharArray()) {
        List<String> lis = new ArrayList<String>();
        String letters = digitToAlpha[digit - '0'];
        for (char c : letters.toCharArray()) {
            for (String temp : res) {
                lis.add(temp + c);
            }
        }
        res = lis;
    }
    return res;
}
```

**Key insight:** By seeding the result list with one empty string and iteratively expanding it digit by digit, the algorithm naturally produces the Cartesian product of all letter groups without recursion or an explicit stack.
