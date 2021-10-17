import java.util.List;

public class LC257_BinaryTreePaths {
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<String>();
    if (root == null) {
      return result;
    }
    List<Integer> temp = new ArrayList();
    helper(root, temp, result);
    return result;
  }

  private void helper(TreeNode root, List<Integer> temp, List<String> result) {
    temp.add(root.val);
    if (root.left == null && root.right == null) {
      result.add(generateString(temp));
    }
    if (root.left != null) {
      helper(root.left, temp, result);
    }
    if (root.right != null) {
      helper(root.right, temp, result);
    }

    temp.remove(temp.size() - 1);
  }

  private String generateString(List<Integer> temp) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < temp.size(); i++) {
      sb.append(temp.get(i));
      if (i + 1 != temp.size()) {
        sb.append("->");
      }
    }
    return sb.toString();
  }
}
