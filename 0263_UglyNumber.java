public class LC263_UglyNumber {
  public boolean isUgly(int num) {
    if (num < 1) {
      return false;
    }
    int[] nums = {2, 3, 5};
    for (int i : nums) {
      while (num % i == 0) {
        num /= i;
      }
    }
    return num == 1;
  }
}
