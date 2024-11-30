import java.util.Arrays;


/**
 * 16. 3Sum Closest
Medium

Given an array nums of n integers and an integer target, find three integers in nums such that the sum
is closest to target. Return the sum of the three integers. You may assume that each input would
have exactly one solution.
Example 1:
Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Constraints:
    3 <= nums.length <= 10^3
    -10^3 <= nums[i] <= 10^3
    -10^4 <= target <= 10^4
 */
public class LC_0016_ThreeSumClosest{
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE, diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] * 3 > target)
                return Math.min(closest, nums[i] + nums[i + 1] + nums[i + 2]);
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int tempDiff = Math.abs(sum - target);

                if (diff > tempDiff) {
                    closest = sum;
                    diff = tempDiff;
                }
                if (sum > target)
                    k--;
                else if (sum < target)
                    j++;
                else
                    return target;
            }
        }
        return closest;
    }
}