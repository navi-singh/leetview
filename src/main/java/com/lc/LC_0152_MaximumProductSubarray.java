package com.lc;

public class LC_0152_MaximumProductSubarray {
  public int maxProduct(int[] nums) {
    if (nums == null || nums.length < 1) {
      return 0;
    }
    int minRes = nums[0], maxRes = nums[0];
    int res = nums[0];
    for (int i = 1; i < nums.length; i++) {
      int temp = maxRes;
      maxRes = Math.max(Math.max(temp * nums[i], minRes * nums[i]), nums[i]);
      minRes = Math.min(Math.min(minRes * nums[i], temp * nums[i]), nums[i]);
      res = Math.max(res, maxRes);
    }
    return res;
  }

  public int maxProduct2(int[] nums) {
    if (nums == null || nums.length < 1) {
      return 0;
    }
    int[] max = new int[nums.length];
    int[] min = new int[nums.length];
    int result = nums[0];
    max[0] = min[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > 0) {
        max[i] = Math.max(nums[i], max[i - 1] * nums[i]);
        min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
      } else {
        max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
        min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
      }
      result = Math.max(result, max[i]);
    }
    return result;
  }
}
