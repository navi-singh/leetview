import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all
 * unique combinations of candidates where the chosen numbers sum to target. You may return the
 * combinations in any order.
 *
 * <p>The same number may be chosen from candidates an unlimited number of times. Two combinations
 * are unique if the frequency of at least one of the chosen numbers is different.
 *
 * <p>It is guaranteed that the number of unique combinations that sum up to target is less than 150
 * combinations for the given input.
 *
 * <p>Example 1:
 *
 * <p>Input: candidates = [2,3,6,7], target = 7 Output: [[2,2,3],[7]] Explanation: 2 and 3 are
 * candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times. 7 is a candidate, and 7 =
 * 7. These are the only two combinations.
 *
 * <p>Example 2:
 *
 * <p>Input: candidates = [2,3,5], target = 8 Output: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * <p>Example 3:
 *
 * <p>Input: candidates = [2], target = 1 Output: []
 *
 * <p>Example 4:
 *
 * <p>Input: candidates = [1], target = 1 Output: [[1]]
 */
public class LC_0039_CombinationSum{
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    Arrays.sort(candidates);
    getCombinations(candidates, 0, target, temp, result);
    return result;
  }

  private void getCombinations(
      int[] candidates, int start, int target, List<Integer> temp, List<List<Integer>> result) {
    if (target == 0) {
      result.add(new ArrayList<Integer>(temp));
      return;
    }
    for (int i = start; i < candidates.length; ++i) {
      if (candidates[i] <= target) {
        temp.add(candidates[i]);
        getCombinations(candidates, i, target - candidates[i], temp, result);
        temp.remove(temp.size() - 1);
      }
    }
  }

  // public List<List<Integer>> permute(int[] nums) {
  // List<List<Integer>> result = new ArrayList<>();
  // helper(0, nums, result);
  // return result;
  // }

  // private void helper(int start, int[] nums, List<List<Integer>> result) {
  // if (start == nums.length - 1) {
  // ArrayList<Integer> list = new ArrayList<>();
  // for (int num : nums) {
  // list.add(num);
  // }
  // result.add(list);
  // return;
  // }

  // for (int i = start; i < nums.length; i++) {
  // swap(nums, i, start);
  // helper(start + 1, nums, result);
  // swap(nums, i, start);
  // }
  // }

  // private void swap(int[] nums, int i, int j) {
  // int temp = nums[i];
  // nums[i] = nums[j];
  // nums[j] = temp;
  // }
}
