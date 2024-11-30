
public class LC_0086_PartitionList {

    public ListNode partition(ListNode head, int x) {
        ListNode smallNodes = new ListNode(-1);
        ListNode restNodes = new ListNode(-1);
        ListNode newHead = smallNodes, remaining = restNodes;
        while (head != null) {
            if (head.val < x) {
                smallNodes.next = head;
                smallNodes = smallNodes.next;
            } else {
                restNodes.next = head;
                restNodes = restNodes.next;
            }
            head = head.next;
        }
        smallNodes.next = remaining.next;
        restNodes.next = null;
        return newHead.next;
    }
}