package com.lc;

/**
 * Definition for singly-linked list. public class LC_0019_RemoveNthNodeFromLast { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
public class LC_0019_RemoveNthNodeFromLast {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) return head;
    ListNode dummyNode = new ListNode(0);
    dummyNode.next = head;
    ListNode curr = dummyNode, node = dummyNode;
    for (int i = 0; i <= n; i++) curr = curr.next;
    while (curr != null) {
      node = node.next;
      curr = curr.next;
    }
    node.next = node.next.next;
    return dummyNode.next;
  }
}
