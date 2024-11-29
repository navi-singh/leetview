public class LC209_MinumumSizeSubarraySum {
  public int minSubArrayLen(int s, int[] nums) {
    int start = 0, sum = 0;
    int maxLen = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      while (sum >= s) {
        maxLen = Math.min(maxLen, i - start + 1);
        sum -= nums[start];
        start++;
      }
    }
    if (maxLen == Integer.MAX_VALUE) {
      return 0;
    }
    return maxLen;
  }
}
