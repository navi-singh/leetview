import LC92_ReverseLinkedList2.ListNode;
import java.util.Stack;

public class LC234_PalindromeLinkedList {
  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    Stack<ListNode> st = new Stack();
    ListNode slow, fast;
    slow = fast = head;
    while (fast != null && fast.next != null) {
      st.push(slow);
      slow = slow.next;
      fast = fast.next.next;
    }
    if (fast != null) {
      slow = slow.next;
    }

    while (!st.isEmpty()) {
      ListNode node = st.pop();
      if (node.val != slow.val) {
        return false;
      }
      slow = slow.next;
    }
    return true;
  }
}
