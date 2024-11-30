public class LC_0137_SingleNumber2 {
  public int singleNumber(int[] nums) {
    int one = 0, two = 0;
    for (int i = 0; i < nums.length; i++) {
      /*
       * IF one has a number already remove it, and it does not have that number
       * appeared previously and it is not there in 2 then add it in one.
       */
      one = (one ^ nums[i]) & ~two;
      /*
       * IF two has a number already remove it, and it does not have that number
       * appeared previously and it is not there in 1 then add it in two.
       */
      two = (two ^ nums[i]) & ~one;
    }
    return one;
  }
}
