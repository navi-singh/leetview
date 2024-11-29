import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC94_InorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> st = new Stack<TreeNode>();
        while (root != null || !st.empty()) {
            if (root != null) {
                st.add(root);
                root = root.left;
            } else {
                root = st.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}