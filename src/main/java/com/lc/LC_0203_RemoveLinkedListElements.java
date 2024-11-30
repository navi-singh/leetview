package com.lc;

public class LC_0203_RemoveLinkedListElements {
  public ListNode removeElements(ListNode head, int val) {
    if (head == null) {
      return head;
    }
    ListNode prev = new ListNode();
    prev.next = head;
    ListNode temp = prev;
    while (head != null) {
      if (head.val == val) {
        temp.next = head.next;
        head.next = null;
        head = temp.next;
      } else {
        temp = temp.next;
        head = head.next;
      }
    }
    return prev.next;
  }
}
