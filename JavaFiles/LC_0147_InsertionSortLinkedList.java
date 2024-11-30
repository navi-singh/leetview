

public class LC_0147_InsertionSortLinkedList {
  public ListNode LC_0147_InsertionSortLinkedList(ListNode head) {
    ListNode fakeHead = new ListNode(Integer.MIN_VALUE);
    while (head != null) {
      ListNode p = fakeHead;
      while (p != null) {
        if (head.val >= p.val && (p.next == null || head.val <= p.next.val)) {
          ListNode next = head.next;
          head.next = p.next;
          p.next = head;
          head = next;
          break;
        }
        p = p.next;
      }
    }
    return fakeHead.next;
  }

  public ListNode insertionSortList(ListNode head) {

    if (head == null || head.next == null) {
      return head;
    }
    ListNode cur = head.next;
    ListNode newHead = new ListNode(head.val);
    while (cur != null) {
      ListNode next = cur.next;
      ListNode tempHead = newHead;
      if (cur.val <= tempHead.val) {
        ListNode oldHead = newHead;
        cur.next = newHead;
        newHead = cur;
      } else {
        while (tempHead.next != null) {
          if (tempHead.val < cur.val && tempHead.next.val >= cur.val) {
            ListNode oldNext = tempHead.next;
            tempHead.next = cur;
            cur.next = oldNext;
          }
          tempHead = tempHead.next;
        }
        if (tempHead.next == null && cur.val > tempHead.val) {
          tempHead.next = cur;
          cur.next = null;
        }
      }
      cur = next;
    }
    return newHead;
  }
}
