package com.lc;

public class LC_0055_JumpGame {
  public boolean canJump(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return true;
    }
    int reach = nums[0];
    for (int i = 0; i < nums.length; i++) {
      if (reach <= i && nums[i] == 0) {
        return false;
      }
      if (reach < i + nums[i]) {
        reach = i + nums[i];
      }
      if (reach >= nums.length - 1) {
        return true;
      }
    }
    return false;
  }
}
