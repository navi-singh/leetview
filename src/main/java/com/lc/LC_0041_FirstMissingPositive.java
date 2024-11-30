package com.lc;

/**
 * Given an unsorted integer array nums, find the smallest missing positive integer.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [1,2,0] Output: 3
 *
 * <p>Example 2:
 *
 * <p>Input: nums = [3,4,-1,1] Output: 2
 *
 * <p>Example 3:
 *
 * <p>Input: nums = [7,8,9,11,12] Output: 1
 */
public class LC_0041_FirstMissingPositive{
  public int firstMissingPositive(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 1;
    }

    for (int i = 0; i < nums.length; i++) {
      while (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {

        int temp = nums[nums[i] - 1];
        nums[nums[i] - 1] = nums[i];
        nums[i] = temp;
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != i + 1) {
        return i + 1;
      }
    }
    return nums.length + 1;
  }
}
