public class LC_0215_KthLargestElementInArray {
  public int findKthLargest(int[] nums, int k) {

    k = nums.length - k;
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      final int j = partition(nums, left, right);
      if (j < k) {
        left = j + 1;
      } else if (j > k) {
        right = j - 1;
      } else {
        break;
      }
    }
    return nums[k];
  }

  private int partition(int[] a, int left, int right) {

    int i = left + 1;
    int j = right;
    while (true) {
      while (i < right && a[i] < a[left]) {
        i++;
      }
      while (j > left && a[left] <= a[j]) {
        j--;
      }
      if (i >= j) {
        break;
      }
      swap(a, i, j);
    }
    swap(a, left, j);
    return j;
  }

  private void swap(int[] nums, int left, int right) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
  }
}
