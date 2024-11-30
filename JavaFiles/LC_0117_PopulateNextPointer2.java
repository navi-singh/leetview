public class LC_0117_PopulateNextPointer2 {
  class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }
  }
  ;

  public Node connect(Node root) {
    Node res = root;
    helper(root);
    return res;
  }

  private void helper(Node root) {
    while (root != null && (root.left == null && root.right == null)) {
      root = root.next;
    }
    if (root == null) {
      return;
    }
    Node left = root.left != null ? root.left : root.right;
    Node cur = left;
    while (root != null) {
      if (cur == root.left) {
        if (root.right != null) {
          cur.next = root.right;
          cur = cur.next;
        }
        root = root.next;
      } else if (cur == root.right) {
        root = root.next;
      } else {
        if (root.left == null && root.right == null) {
          root = root.next;
        } else {
          cur.next = root.left != null ? root.left : root.right;
          cur = cur.next;
        }
      }
    }
    helper(left);
  }
}
