import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 22. Generate Parentheses
Medium

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
Input: n = 1
Output: ["()"]

Constraints:
    1 <= n <= 8
 *
 */

 public class LC_0022_GenerateParentheses{
    public List<String> generateParenthesis(int n) {
        Set<String> res = new HashSet<>();
        helper(n, n, "", res);
        return new ArrayList<>(res);
    }

    private void helper(int left, int right, String str, Set<String> res) {
        if (left < 0 || right < 0 || left > right)
            return;
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        helper(left - 1, right, str + "(", res);
        helper(left, right - 1, str + ")", res);
    }
}