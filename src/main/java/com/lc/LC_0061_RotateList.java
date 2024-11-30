package com.lc;

/**
 * Definition for singly-linked list. public class LC_0061_RotateList { int val; ListNode next;
 * ListNode(int x) { val = x; } }
 */
public class LC_0061_RotateList {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null) return head;
    ListNode iter = head;
    int len = 1;
    while (iter.next != null) {
      iter = iter.next;
      len++;
    }
    iter.next = head;
    k = len - k % len;
    while (k-- > 0) {
      iter = iter.next;
    }
    head = iter.next;
    iter.next = null;
    return head;
  }
}
