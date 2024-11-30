package com.lc;

public class LC_0024_SwapPairsInList {
  public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode();
    dummy.next = head;
    ListNode prev = dummy;
    while (head != null && head.next != null) {
      ListNode temp = head.next.next;
      prev.next = head.next;
      head.next.next = head;
      head.next = temp;
      prev = head;
      head = temp;
    }
    return dummy.next;
  }
}
