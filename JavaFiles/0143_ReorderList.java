class LC143_ReorderList {
  public class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public void reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    ListNode second = slow.next;
    slow.next = null;
    ListNode first = head;
    second = reverseList(second);
    while (second != null) {
      ListNode p1 = first.next;
      ListNode p2 = second.next;
      first.next = second;
      second.next = p1;
      second = p2;
      first = p1;
    }
  }

  private ListNode reverseList(ListNode head) {
    ListNode pre = head, curr = head.next;
    while (curr != null) {
      ListNode temp = curr.next;
      curr.next = pre;
      pre = curr;
      curr = temp;
    }
    head.next = null;
    return pre;
  }
}
