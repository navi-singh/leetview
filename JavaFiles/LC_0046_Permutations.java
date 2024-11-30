import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Example 1:
 *
 * <p>Input: nums = [1,2,3] Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * <p>Example 2:
 *
 * <p>Input: nums = [0,1] Output: [[0,1],[1,0]]
 *
 * <p>Example 3:
 *
 * <p>Input: nums = [1] Output: [[1]]
 */
public class LC_0046_Permutations{
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    perm(nums, 0, nums.length, res);
    return res;
  }

  private void perm(int[] nums, int k, int n, List<List<Integer>> res) {
    if (k == n) {
      res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
      return;
    } else {
      for (int i = k; i < n; ++i) {
        swap(nums, i, k);
        perm(nums, k + 1, n, res);
        swap(nums, i, k);
      }
    }
  }

  private static final void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }
}
