public class LC155_MinStack {
  class MinStack {
    class Node {
      public int val;
      public int min;
      public Node next;

      public Node(int val, int min) {
        this.val = val;
        this.min = min;
      }
    }

    Node top;

    /** initialize your data structure here. */
    public MinStack() {}

    public void push(int x) {
      if (top == null) {
        top = new Node(x, x);
      } else {
        Node temp = new Node(x, top.min > x ? x : top.min);
        temp.next = top;
        top = temp;
      }
    }

    public void pop() {
      if (top == null) {
        return;
      } else {
        Node ret = top.next;
        top.next = null;
        top = ret;
      }
    }

    public int top() {
      if (top == null) {
        return -1;
      }
      return top.val;
    }

    public int getMin() {
      if (top == null) {
        return -1;
      }
      return top.min;
    }
  }
}
