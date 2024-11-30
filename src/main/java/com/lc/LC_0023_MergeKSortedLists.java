package com.lc; /**
 * Definition for singly-linked list. public class LC_0023_MergeKSortedLists { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */

import java.util.PriorityQueue;
import java.util.Queue;


/**
 * Time complexity: O(nlogk)
 * Space complexity: o(k)
 */
public class LC_0023_MergeKSortedLists{
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        Queue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val); // min-heap

        for (final ListNode list : lists)
            if (list != null)
                pq.offer(list);

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            if (minNode.next != null)
                pq.offer(minNode.next);
            curr.next = minNode;
            curr = curr.next;
        }

        return dummy.next;
    }
}

//public class LC_0023_MergeKSortedLists{
//    public ListNode mergeKLists(ListNode[] lists) {
//        int length = lists.length;
//        for (int processed = 1; processed < length; processed *= 2) {
//            for (int i = 0; i < length - processed; i = processed * 2) {
//                lists[i] = mergeTwoLists(lists[i], lists[i + processed]);
//            }
//        }
//        return length > 0 ? lists[0] : null;
//    }
//
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode dummy = new ListNode();
//        ListNode curr = dummy;
//        while (l1 != null && l2 != null) {
//            if (l1.val <= l2.val) {
//                curr.next = l1;
//                l1 = l1.next;
//            } else {
//                curr.next = l2;
//                l2 = l2.next;
//            }
//            curr = curr.next;
//        }
//        curr.next = l1 != null ? l1 : l2;
//        return dummy.next;
//    }
//}
