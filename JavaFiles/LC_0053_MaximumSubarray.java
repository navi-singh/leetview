class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int tempMax = 0;
        for (int i = 0; i < nums.length; ++i) {
            tempMax += nums[i];
            if (tempMax > max) {
                max = tempMax;
            }
            if (tempMax < 0) {
                tempMax = 0;
            }
        }
        return max;
    }
}