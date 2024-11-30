package com.lc;

public class LC_0083_RemoveDuplicatesSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head;
        ListNode next = head.next;

        while (next != null) {
            if (head.val != next.val) {
                head.next = next;
                head = head.next;
            }
            next = next.next;
        }
        if (head.next != null && head.val == head.next.val) {
            head.next = null;
        }
        return temp;
    }
}