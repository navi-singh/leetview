
public class LC_0080_RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) {
            return nums.length;
        }
        int last = 1, next = 2;
        for (; next < nums.length; next++) {
            if (nums[last - 1] != nums[next]) {
                last++;
                nums[last] = nums[next];
            }
        }
        return last + 1;
    }
}