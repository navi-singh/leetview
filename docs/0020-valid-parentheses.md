---
description: EASY
---

# 20. Valid Parentheses

Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

**Example 1:**

```text
Input: s = "()"
Output: true
```

**Example 2:**

```text
Input: s = "()[]{}"
Output: true
```

**Example 3:**

```text
Input: s = "(]"
Output: false
```

**Example 4:**

```text
Input: s = "([)]"
Output: false
```

**Example 5:**

```text
Input: s = "{[]}"
Output: true
```

**Constraints:**

- `1 <= s.length <= 10^4`
- `s` consists of parentheses only `'()[]{}'`.

---

## Approach 1: Stack with HashMap (Cleaner)

Use a stack to track open brackets. Build a HashMap mapping each closing bracket to its expected opening bracket. When a closing bracket is encountered, pop from the stack and verify it matches. If the stack is empty at the end, all brackets were properly matched and closed.

#### Complexity Analysis

- **Time complexity: O(n).** Each character is pushed or popped from the stack at most once.
- **Space complexity: O(n).** In the worst case (all open brackets), the stack holds `n/2` elements.

```java
public boolean isValidUsingMap(String s) {
    Stack<Character> st = new Stack<>();
    Map<Character, Character> mapping =
        new HashMap<Character, Character>() {
          {
            put(')', '(');
            put(']', '[');
            put('}', '{');
          }
        };
    for (int i = 0; i < s.length(); ++i) {
        if (mapping.containsKey(s.charAt(i))) {
            Character c = mapping.get(s.charAt(i));
            if (st.empty() || (!st.empty() && st.pop() != c)) {
                return false;
            }
        } else {
            st.push(s.charAt(i));
        }
    }
    return st.empty();
}
```

## Approach 2: Stack with Direct Comparison (Faster)

Same stack-based logic, but instead of a HashMap, directly compare the closing bracket to the expected open bracket using `peek()`. This avoids map lookups and is faster in practice with less memory overhead, though it requires explicit checks for each bracket type.

#### Complexity Analysis

- **Time complexity: O(n).** Single pass through the string with O(1) stack operations.
- **Space complexity: O(n).** Stack holds at most `n/2` open brackets.

```java
public boolean isValid(String s) {
    Stack<Character> parentheses = new Stack<Character>();
    for (int i = 0; i < s.length(); ++i) {
        if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
            parentheses.push(s.charAt(i));
        else {
            if (parentheses.empty()) return false;
            if (s.charAt(i) == ')' && parentheses.peek() != '(') return false;
            if (s.charAt(i) == ']' && parentheses.peek() != '[') return false;
            if (s.charAt(i) == '}' && parentheses.peek() != '{') return false;
            parentheses.pop();
        }
    }
    return parentheses.empty();
}
```

**Key insight:** A stack perfectly models the LIFO nesting requirement of parentheses — the most recently opened bracket must always be the next one closed. Checking `st.empty()` at the end catches unmatched open brackets that were never closed.
