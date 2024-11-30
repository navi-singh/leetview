public class LC_0075_SortColors {
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1;
        for (int i = 0; i < nums.length && i <= right; i++) {
            if (nums[i] == 0) {
                swap(nums, left, i);
                left++;
            } else if (nums[i] == 2) {
                swap(nums, right, i);
                i--;
                right--;
            }
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}