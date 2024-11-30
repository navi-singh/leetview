package com.lc;

public class LC_0160_IntersectionLists {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int len1 = getLen(headA), len2 = getLen(headB);
    ListNode p1 = headA, p2 = headB;
    if (len1 > len2) {
      int diff = len1 - len2;
      while (diff > 0) {
        p1 = p1.next;
        diff--;
      }
    } else {
      int diff = len2 - len1;
      while (diff > 0) {
        p2 = p2.next;
        diff--;
      }
    }
    while (p1 != p2) {
      p1 = p1.next;
      p2 = p2.next;
    }
    return p1;
  }

  private int getLen(ListNode root) {
    int len = 0;
    while (root != null) {
      root = root.next;
      len++;
    }
    return len;
  }
}
