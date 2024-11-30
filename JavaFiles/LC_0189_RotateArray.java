public class LC_0189_RotateArray {
  public void rotate(int[] nums, int k) {
    if (nums == null) {
      return;
    }
    int len = nums.length;
    if (k > len) {
      k = k % len;
    }
    int temp;
    reverse(nums, 0, len - k - 1);
    reverse(nums, len - k, len - 1);
    reverse(nums, 0, len - 1);
  }

  private void reverse(int[] nums, int start, int end) {
    int temp;
    while (start < end) {
      temp = nums[start];
      nums[start] = nums[end];
      nums[end--] = temp;
      start++;
    }
  }
}
