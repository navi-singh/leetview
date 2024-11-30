package com.lc;

public class LC_0092_ReverseLinkedList2 {
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

  public ListNode reverseBetween(ListNode head, int m, int n) {
    if (head == null) {
      return head;
    }
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    head = dummy;
    int num = 1;
    ListNode prev = null;
    while (head != null && num++ < m) {
      head = head.next;
    }
    ListNode curr = head.next;
    while (curr != null && num <= n) {
      ListNode nex = curr.next;
      curr.next = nex.next;
      nex.next = head.next;
      head.next = nex;
      num++;
    }
    return dummy.next;
  }
}
