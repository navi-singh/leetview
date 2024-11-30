import java.util.ArrayList;
import java.util.List;

public class LC_0216_Combination3 {
  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> temp = new ArrayList<Integer>();
    helper(k, n, temp, result, 1);
    return result;
  }

  private void helper(int k, int sum, List<Integer> temp, List<List<Integer>> result, int i) {
    if (sum < 0) {
      return;
    }
    if (k == temp.size() && sum == 0) {
      result.add(new ArrayList<Integer>(temp));
      return;
    }

    for (; i < 10; i++) {
      temp.add(i);
      helper(k, sum - i, temp, result, i + 1);
      temp.remove(temp.size() - 1);
    }
  }
}
