public class LC238_ProductArray {
  public int[] productExceptSelf(int[] nums) {
    if (nums == null || nums.length < 1) {
      return null;
    }
    int[] result = new int[nums.length];
    result[0] = 1;
    for (int i = 1; i < nums.length; i++) {
      result[i] = result[i - 1] * nums[i - 1];
    }
    int rightSum = 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      result[i] = result[i] * rightSum;
      rightSum *= nums[i];
    }
    return result;
  }
}
