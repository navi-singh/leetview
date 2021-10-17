
public class LC83_RemoveDuplicatesSortedList {
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