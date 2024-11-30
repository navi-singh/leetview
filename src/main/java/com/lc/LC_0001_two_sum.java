package com.lc;

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

Time: O(n)
Space: O(n)
*/

import java.util.HashMap;
import java.util.Map;

public class LC_0001_two_sum {
  public int[] twoSum(int[] nums, int target) {
    // for(int index=0; index < nums.length-1; index++) {
    //     for(int otherIndex = index+1; otherIndex < nums.length; otherIndex++) {
    //         if((nums[index] + nums[otherIndex]) == target)
    //             return new int[]{index, otherIndex};
    //     }
    // }
    // return new int[0];
    Map<Integer, Integer> requiredElements = new HashMap<Integer, Integer>();
    for (int index = 0; index < nums.length; index++) {
      if (requiredElements.containsKey(nums[index])) {
        return new int[] {index, requiredElements.get(nums[index])};
      }
      requiredElements.put(target - nums[index], index);
    }
    return null;
  }
}
