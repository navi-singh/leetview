import java.util.List;
import java.util.ArrayList;


public class LC_0144_PreorderTraversal {
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    preorderTraversal(root, res);
    return res;
  }

  private void preorderTraversal(TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }
    res.add(root.val);
    preorderTraversal(root.left, res);
    preorderTraversal(root.right, res);
  }
}
