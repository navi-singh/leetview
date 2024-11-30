package com.lc;

public class LC_0237_DeleteNodeInLinkedList {
  public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
  }
}
