import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class LC_0020_ValidParentheses{
  public boolean isValidUsingMap(String s) {
    Stack<Character> st = new Stack<>();
    Map<Character, Character> mapping =
        new HashMap<>() {
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

  // Below one is faster and use less memory but its uglier
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
}
