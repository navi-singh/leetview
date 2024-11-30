package com.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all
 * unique combinations in candidates where the candidate numbers sum to target.
 *
 * <p>Each number in candidates may only be used once in the combination.
 *
 * <p>Note: The solution set must not contain duplicate combinations.
 *
 * <p>Example 1:
 *
 * <p>Input: candidates = [10,1,2,7,6,1,5], target = 8 Output: [ [1,1,6], [1,2,5], [1,7], [2,6] ]
 *
 * <p>Example 2:
 *
 * <p>Input: candidates = [2,5,2,1,2], target = 5 Output: [ [1,2,2], [5] ]
 */
public class LC_0040_CombinationSumII{
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
      if (i > start && candidates[i] == candidates[i - 1]) continue;
      if (candidates[i] <= target) {
        temp.add(candidates[i]);
        getCombinations(candidates, i + 1, target - candidates[i], temp, result);
        temp.remove(temp.size() - 1);
      }
    }
  }
}
