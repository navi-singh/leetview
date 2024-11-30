package com.lc;

public class LC_0138_CopyListWithRandomPointer {
  class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }

  public Node copyRandomList(Node head) {
    if (head == null) {
      return head;
    }
    Node p = head;
    while (p != null) {
      Node temp = new Node(p.val);
      temp.next = p.next;
      p.next = temp;
      p = p.next.next;
    }
    p = head;
    while (p != null) {
      p.next.random = p.random != null ? p.random.next : null;
      p = p.next.next;
    }
    p = head;
    Node newHead = p.next;
    while (p != null) {
      Node temp = p.next;
      p.next = temp.next;
      if (temp.next != null) {
        temp.next = temp.next.next;
      }
      p = p.next;
    }
    return newHead;
  }
}
