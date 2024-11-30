package com.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC_0090_Subsets2 {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Set<List<Integer>> res = new HashSet<List<Integer>>();
    List<Integer> temp = new ArrayList<Integer>();
    res.add(new ArrayList<Integer>(temp));
    Arrays.sort(nums);
    helper(res, temp, 0, nums);
    return new ArrayList<>(res);
  }

  private void helper(Set<List<Integer>> res, List<Integer> temp, int index, int[] nums) {
    for (int i = index; i < nums.length; i++) {
      if (i > index && nums[i] == nums[i - 1]) {
        continue;
      }
      temp.add(nums[i]);
      res.add(new ArrayList<Integer>(temp));
      helper(res, temp, i + 1, nums);
      temp.remove(temp.size() - 1);
    }
  }
}
