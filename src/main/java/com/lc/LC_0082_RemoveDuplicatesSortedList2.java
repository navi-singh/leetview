package com.lc;

public class LC_0082_RemoveDuplicatesSortedList2 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = new ListNode(-1);
        temp.next = head;
        ListNode prev = temp;
        ListNode next = head.next;
        boolean dups = false;
        while (next != null) {
            if (next.val == prev.next.val) {
                dups = true;
            } else if (next.val != prev.next.val) {
                if (dups) {
                    prev.next = next;
                    dups = false;
                } else {
                    prev = prev.next;
                }
            }
            next = next.next;
        }
        if (dups) {
            prev.next = null;
        }
        return temp.next;
    }
}
