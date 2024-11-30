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
