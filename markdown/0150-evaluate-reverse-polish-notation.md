---
description: MEDIUM
---

# 150. Evaluate Reverse Polish Notation

You are given an array of strings `tokens` that represents an arithmetic expression in a Reverse Polish Notation.

Evaluate the expression. Return an integer that represents the value of the expression.

**Note that:**
- The valid operators are `'+'`, `'-'`, `'*'`, and `'/'`.
- Each operand may be an integer or another expression.
- The division between two integers always truncates toward zero.
- There will not be any division by zero.
- The input represents a valid arithmetic expression in a reverse polish notation.
- The answer and all the intermediate calculations can be represented in a **32-bit** integer.

**Example 1:**

```text
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
```

**Example 2:**

```text
Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
```

**Example 3:**

```text
Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
```

**Constraints:**

- `1 <= tokens.length <= 10^4`
- `tokens[i]` is either an operator `"+"`, `"-"`, `"*"`, or `"/"`, or an integer in the range `[-200, 200]`.

---

## Approach: Stack-Based Evaluation

Push operands onto a stack. When an operator is encountered, pop the top two operands, apply the operator (note: the second-popped value is the left operand), and push the result back. The final answer is the sole remaining element on the stack.

#### Complexity Analysis

- **Time complexity: O(n).** Each token is processed exactly once.
- **Space complexity: O(n).** In the worst case (all operands, no operators), all tokens are on the stack simultaneously.

```java
package com.lc;

import java.util.Stack;

public class LC_0150_EvaluatePolish {
  public int evalRPN(String[] tokens) {
    int res = 0;
    Stack<Integer> st = new Stack<Integer>();
    String operators = "+-*/";
    for (String s : tokens) {
      if (!operators.contains(s)) {
        st.add(Integer.valueOf(s));
      } else {
        int first = st.pop();
        int second = st.pop();
        int opIndex = operators.indexOf(s);
        switch (opIndex) {
          case 0:
            st.push(first + second);
            break;
          case 1:
            st.push(second - first);
            break;
          case 2:
            st.push(second * first);
            break;
          case 3:
            st.push(second / first);
            break;
        }
      }
    }
    res = st.pop();
    return res;
  }
}
```

**Key insight:** The order of operands matters for subtraction and division — `second` (popped second, placed first) is the left operand and `first` (popped first, placed second) is the right operand, matching the original infix expression order.
