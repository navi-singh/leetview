public class LC_0136_SingleNumber {
  public int singleNumber(int[] nums) {
    int result = 0;
    for (int num : nums) {
      result ^= num;
      System.out.println(num + ":" + result);
    }
    return 0;
  }
}
