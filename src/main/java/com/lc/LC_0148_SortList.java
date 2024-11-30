package com.lc;

public class LC_0148_SortList {
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode slow = head, fast = head, pre = head;
    while (fast != null && fast.next != null) {
      pre = slow;
      fast = fast.next.next;
      slow = slow.next;
    }
    pre.next = null;
    return merge(sortList(head), sortList(slow));
  }

  private ListNode merge(ListNode first, ListNode sec) {
    if (first == null) return sec;
    if (sec == null) return first;
    if (first.val < sec.val) {
      first.next = merge(first.next, sec);
      return first;
    } else {
      sec.next = merge(first, sec.next);
      return sec;
    }
  }
}
