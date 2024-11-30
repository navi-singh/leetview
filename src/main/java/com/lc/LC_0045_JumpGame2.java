package com.lc;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of
 * the array.
 *
 * <p>Each element in the array represents your maximum jump length at that position.
 *
 * <p>Your goal is to reach the last index in the minimum number of jumps.
 *
 * <p>You can assume that you can always reach the last index.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [2,3,1,1,4] Output: 2 Explanation: The minimum number of jumps to reach the last
 * index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * <p>Example 2:
 *
 * <p>Input: nums = [2,3,0,1,4] Output: 2
 */
public class LC_0045_JumpGame2 {
  public int jump(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int jump = 0, reach = 0, lastReach = 0;
    for (int i = 0; i <= reach && i < nums.length; ++i) {
      if (i > lastReach) {
        jump++;
        lastReach = reach;
      }
      reach = Math.max(reach, i + nums[i]);
    }
    if (reach < nums.length - 1) {
      return 0;
    }
    return jump;
  }
}
