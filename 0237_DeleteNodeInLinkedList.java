import LC143_ReorderList.ListNode;

public class LC237_DeleteNodeInLinkedList {
  public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
  }
}
