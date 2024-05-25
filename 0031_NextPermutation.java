/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 *
 * <p>
 * If such an arrangement is not possible, it must rearrange it as the lowest
 * possible order (i.e., sorted in ascending order).
 *
 * <p>
 * The replacement must be in place and use only constant extra memory.
 *
 * <p>
 * Example 1:
 *
 * <p>
 * Input: nums = [1,2,3] Output: [1,3,2]
 *
 * <p>
 * Example 2:
 *
 * <p>
 * Input: nums = [3,2,1] Output: [1,2,3]
 *
 * <p>
 * Example 3:
 *
 * <p>
 * Input: nums = [1,1,5] Output: [1,5,1]
 * <p>
 * Complete printout 1,2,3,4 -> 1,2,4,3,-> 1,3,2,4,-> 1,3,4,2,-> 1,4,2,3,->
 * 1,4,3,2,-> 2,1,3,4,-> 2,1,4,3,-> 2,3,1,4,-> 2,3,4,1,-> 2,4,1,3,-> 2,4,3,1,->
 * 3,1,2,4,-> 3,1,4,2,-> 3,2,1,4,-> 3,2,4,1,-> 3,4,1,2,-> 3,4,2,1,-> 4,1,2,3,->
 * 4,1,3,2,-> 4,2,1,3,-> 4,2,3,1,-> 4,3,1,2,-> 4,3,2,1,->
 */
class Solution {
  public void nextPermutation(int[] nums) {
    if (nums.length <= 1)
      return;
    int total = nums.length;
    int swapIndex = total - 2;
    while (swapIndex >= 0 && nums[swapIndex] >= nums[swapIndex + 1])
      swapIndex--;
    if (swapIndex >= 0) {
      int index = total - 1;
      while (index >= 0 && nums[swapIndex] >= nums[index])
        index--;
      swap(nums, swapIndex, index);
    }
    reverse(nums, swapIndex + 1, total - 1);
  }

  private void reverse(int[] nums, int first, int second) {
    while (first < second) swap(nums, first++, second--);
  }
  private void swap(int[] nums,int first, int second){
    int temp = nums[first];
    nums[first] = nums[second];
     nums[second] = temp;
  }
}
