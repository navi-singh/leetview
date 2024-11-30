/**
 * 21. Merge Two Sorted Lists
Easy
Merge two sorted linked lists and return it as a new sorted list. 
The new list should be made by splicing together the nodes of the first two lists.

Example 1:
Input: l1 = [1,2,4], l2 = [1,3,4]
Output: [1,1,2,3,4,4]
 * Definition for singly-linked list. public class LC_0021_MergeTwoLists { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
public class LC_0021_MergeTwoLists{
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
}