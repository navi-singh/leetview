public class LC162_FindPeakElement {
  public int findPeakElement(int[] nums) {
    if (nums == null || nums.length < 0) {
      return 0;
    }
    int peak = -1;
    int len = nums.length;
    if (len == 1 || nums[0] > nums[1]) {
      return 0;
    }

    for (int i = 1; i < nums.length - 1; i++) {
      if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
        return i;
      }
    }
    if (nums[len - 1] > nums[len - 2]) {
      return len - 1;
    }
    return -1;
  }
}
