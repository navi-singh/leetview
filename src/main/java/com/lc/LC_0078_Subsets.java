package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0078_Subsets {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    res.add(new ArrayList<Integer>());
    if (nums.length < 1) {
      return res;
    }
    for (int i = 0; i < nums.length; i++) {
      int size = res.size();
      for (int j = 0; j < size; j++) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(res.get(j));
        temp.add(nums[i]);
        res.add(temp);
      }
    }

    return res;
  }
}
