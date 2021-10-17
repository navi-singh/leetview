import java.util.ArrayList;
import java.util.List;

public class LC113_PathSum2 {
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> res = new ArrayList();
    List<Integer> temp = new ArrayList<Integer>();
    pathSumHelper(root, sum, 0, temp, res);
    return res;
  }

  private void pathSumHelper(
      TreeNode root, int sum, int current, List<Integer> temp, List<List<Integer>> res) {
    if (root == null) {
      return;
    }
    current += root.val;
    temp.add(root.val);
    if (root.left == null && root.right == null) {
      if (sum == current) {
        res.add(new ArrayList(temp));
      }
    }
    pathSumHelper(root.left, sum, current, temp, res);
    pathSumHelper(root.right, sum, current, temp, res);
    temp.remove(temp.size() - 1);
  }
}
