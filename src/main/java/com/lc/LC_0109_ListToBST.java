package com.lc;

public class LC_0109_ListToBST {
  static ListNode h;

  public TreeNode sortedListToBST(ListNode head) {
    if (head == null) {
      return null;
    }
    this.h = head;
    ListNode temp = head;
    int size = 0;
    while (temp != null) {
      temp = temp.next;
      size++;
    }
    return helper(0, size - 1);
  }

  private TreeNode helper(int left, int right) {
    if (left > right) {
      return null;
    }
    int mid = left + (right - left) / 2;
    TreeNode leftNode = helper(left, mid - 1);
    TreeNode node = new TreeNode(h.val);

    h = h.next;
    TreeNode rightNode = helper(mid + 1, right);
    node.left = leftNode;
    node.right = rightNode;
    return node;
  }
}
