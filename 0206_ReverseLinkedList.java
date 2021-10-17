import LC143_ReorderList.ListNode;

public class LC206_ReverseLinkedList {
  public ListNode reverseList(ListNode head) {
    if (head == null) {
      return head;
    }
    ListNode temp = new ListNode();
    temp.next = head;
    ListNode prev = null;
    while (head.next != null) {
      temp.next = head.next;
      head.next = prev;
      prev = head;
      head = temp.next;
    }
    temp.next.next = prev;
    return temp.next;
  }
}
