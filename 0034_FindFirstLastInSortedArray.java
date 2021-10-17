/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position
 * of a given target value.
 *
 * <p>If target is not found in the array, return [-1, -1].
 *
 * <p>Follow up: Could you write an algorithm with O(log n) runtime complexity?
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [5,7,7,8,8,10], target = 8 Output: [3,4]
 *
 * <p>Example 2:
 *
 * <p>Input: nums = [5,7,7,8,8,10], target = 6 Output: [-1,-1]
 *
 * <p>Example 3:
 *
 * <p>Input: nums = [], target = 0 Output: [-1,-1]
 */
class Solution {
  public int[] searchRange(int[] nums, int target) {
    int[] res = {-1, -1};
    int left = binarySearch(nums, target, true);
    if (left == nums.length || nums[left] != target) {
      return res;
    }
    res[0] = left;
    res[1] = binarySearch(nums, target, false) - 1;
    return res;
  }

  private int binarySearch(int[] nums, int target, boolean isLeft) {
    int left = 0, right = nums.length;

    while (left < right) {
      int mid = left + (right - left) / 2;
      if ((nums[mid] > target) || (isLeft && nums[mid] == target)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }
}
