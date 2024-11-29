import java.util.LinkedList;
import java.util.Queue;

public class LC225_StackQueues {
  Queue<Integer> st;

  /** Initialize your data structure here. */
  public LC225_StackQueues() {
    st = new LinkedList<Integer>();
  }

  /** Push element x onto stack. */
  public void push(int x) {
    int size = st.size();
    st.add(x);
    for (int i = 0; i < size; i++) {
      Integer elem = st.poll();
      st.add(elem);
    }
  }

  /** Removes the element on top of the stack and returns that element. */
  public int pop() {
    if (st.isEmpty()) {
      return -1;
    }
    return Integer.valueOf(st.poll().toString());
  }

  /** Get the top element. */
  public int top() {
    return st.peek();
  }

  /** Returns whether the stack is empty. */
  public boolean empty() {
    return st.isEmpty();
  }
}
