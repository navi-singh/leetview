package com.lc;

/**
 * Given a sorted array of distinct integers and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in order.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [1,3,5,6], target = 5 Output: 2
 *
 * <p>Example 2:
 *
 * <p>Input: nums = [1,3,5,6], target = 2 Output: 1
 *
 * <p>Example 3:
 *
 * <p>Input: nums = [1,3,5,6], target = 7 Output: 4
 *
 * <p>Example 4:
 *
 * <p>Input: nums = [1,3,5,6], target = 0 Output: 0
 *
 * <p>Example 5:
 *
 * <p>Input: nums = [1], target = 0 Output: 0
 */
public class LC_0035_SearchInsertPosition {
  public int searchInsert(int[] nums, int target) {
    int left = 0, right = nums.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }
}
