package com.lc;

import java.util.Stack;

/**
 * Example 1:
 *
 * <p>Input: s = "(()" Output: 2 Explanation: The longest valid parentheses substring is "()".
 *
 * <p>Example 2:
 *
 * <p>Input: s = ")()())" Output: 4 Explanation: The longest valid parentheses substring is "()()".
 *
 * <p>Example 3:
 *
 * <p>Input: s = "" Output: 0
 */
public class LC_0032_LongestValidParentheses{
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
}
