/**
 * Input: nums = [0,0,1,1,1,2,2,3,3,4] Output: 5, nums = [0,1,2,3,4]
 *
 * <p>Explanation: Your function should return length = 5, with the first five elements of nums
 * being modified to 0, 1, 2, 3, and 4 respectively. It doesn't matter what values are set beyond
 * the returned length.
 */
class Solution {
  public int removeDuplicates(int[] nums) {
    if (nums.length < 2) return nums.length;
    int newIndex = 0;
    for (int index = 1; index < nums.length; index++) {
      if (nums[index] != nums[newIndex]) {
        newIndex += 1;
        nums[newIndex] = nums[index];
      }
    }
    return newIndex + 1;
  }
}
