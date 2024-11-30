package com.lc;

/**
 * Input: head = [1,2,3,4,5], k = 3 Output: [3,2,1,4,5]
 *
 * <p>Example 3:
 *
 * <p>Input: head = [1,2,3,4,5], k = 1 Output: [1,2,3,4,5]
 *
 * <p>Example 4:
 *
 * <p>Input: head = [1], k = 1 Output: [1]
 *
 * <p>Definition for singly-linked list. public class LC_0025_ReverseNodesKGroup { int val; ListNode next;
 * ListNode(int x) { val = x; } }
 */
public class LC_0025_ReverseNodesKGroup{

  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null) return head;
    ListNode dummy = new ListNode(0);
    ListNode prev = dummy;
    dummy.next = head;
    ListNode curr = head;
    for (int i = 1; curr != null; ++i) {
      if (i % k == 0) {
        prev = reverseList(prev, curr.next);
        curr = prev.next;
      } else curr = curr.next;
    }
    return dummy.next;
  }

  private ListNode reverseList(ListNode prev, ListNode next) {
    ListNode curr = prev.next;
    ListNode tempNext = curr.next;
    while (tempNext != next) {
      curr.next = tempNext.next;
      tempNext.next = prev.next;
      prev.next = tempNext;
      tempNext = curr.next;
    }
    return curr;
  }
}
