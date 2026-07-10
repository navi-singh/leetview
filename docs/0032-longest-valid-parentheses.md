---
description: HARD
---

# 32. Longest Valid Parentheses

Given a string containing just the characters `'('` and `')'`, return the length of the longest valid (well-formed) parentheses substring.

**Example 1:**

```text
Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".
```

**Example 2:**

```text
Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".
```

**Example 3:**

```text
Input: s = ""
Output: 0
```

**Constraints:**

- `0 <= s.length <= 3 * 10^4`
- `s[i]` is `'('` or `')'`

---

## Approach 1: Stack

Push the index `-1` as a base sentinel onto the stack. For each `(`, push its index. For each `)`, pop the top. If the stack becomes empty, push the current index as the new base. Otherwise, the current valid substring length is `index - stack.peek()`, and we update the max.

#### Complexity Analysis

- **Time complexity: O(n).** A single left-to-right pass.
- **Space complexity: O(n).** In the worst case (all `(`), every index is pushed onto the stack.

```java
  public int longestValidParenthesesWithStack(String s) {
    int maxLength = 0;
    if (s.isEmpty()) return maxLength;
    Stack<Integer> st = new Stack<Integer>();
    st.push(-1);
    for (int index = 0; index < s.length(); ++index) {
      if (s.charAt(index) == '(') {
        st.push(index);
      } else {
        st.pop();
        if (st.isEmpty()) {
          st.push(index);
        } else {
          maxLength = Math.max(maxLength, index - st.peek());
        }
      }
    }
    return maxLength;
  }
```

**Key insight:** Storing indices (rather than characters) on the stack lets us compute substring lengths directly. The sentinel `-1` ensures that even the very first valid pair produces a correct length of `index - (-1) = index + 1`.

---

## Approach 2: Two-Pass Counter (O(1) Space)

Make a left-to-right pass counting `(` and `)`. When counts are equal, update the max with `2 * right`. When `)` exceeds `(`, reset both counters. Then repeat right-to-left (resetting when `(` exceeds `)`). This two-pass approach handles cases where one direction misses a valid substring.

#### Complexity Analysis

- **Time complexity: O(n).** Two linear passes.
- **Space complexity: O(1).** Only four integer counters are used.

```java
  public int longestValidParentheses(String s) {
    int maxLength = 0;
    if (s.isEmpty()) return maxLength;
    int left = 0, right = 0;
    for (int index = 0; index < s.length(); index++) {
      if (s.charAt(index) == '(') left++;
      else right++;
      if (left == right) {
        maxLength = Math.max(maxLength, 2 * right);
      } else if (right >= left) {
        left = right = 0;
      }
    }
    left = right = 0;
    for (int index = s.length() - 1; index >= 0; index--) {
      if (s.charAt(index) == '(') left++;
      else right++;
      if (left == right) {
        maxLength = Math.max(maxLength, 2 * left);
      } else if (left >= right) {
        left = right = 0;
      }
    }
    return maxLength;
  }
```

**Key insight:** A single left-to-right pass cannot detect valid substrings that begin with excess `)`. The reverse pass catches those cases by treating `(` as the "excess" character that triggers a reset, making the combined approach complete without any extra memory.
