public class LC154_MinimumInRotatedArray {
  public int findMin(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int left = 0, right = nums.size() - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] < nums[right]) {
        right = mid;
      } else if (nums[mid] > nums[right]) {
        left = mid + 1;
      } else {
        right--;
      }
    }
    return nums[left];
  }

  public int findMin(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int start = 0, end = nums.length - 1;
    while (start <= end) {
      while (nums[start] == nums[end] && start != end) {
        start++;
      }
      if (nums[start] <= nums[end]) {
        return nums[start];
      }
      int mid = start + (end - start) / 2;
      if (nums[start] <= nums[mid]) {
        start = mid + 1;
      } else {
        end = mid;
      }
    }
    return -1;
  }
}
