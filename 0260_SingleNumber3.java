/**
 * http://yucoding.blogspot.com/2016/12/leetcode-question-single-number-iii.html Given an array of
 * numbers nums, in which exactly two elements appear only once and all the other elements appear
 * exactly twice. Find the two elements that appear only once.
 *
 * <p>Example:
 *
 * <p>Input: [1,2,1,3,2,5] Output: [3,5]
 *
 * <p>Note:
 *
 * <p>The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only
 * constant space complexity?
 *
 * <p>Solution
 *
 * <p>Analysis: From the previous questions we know that the bit manipulation would be a good start
 * to go, same as this problem. The hard part is by XOR all the values in the array, we only have
 * the XOR of the two numbers we have, say x = a^b.
 *
 * <p>From the figure I draw below, it is easy to see that: the essential usage of XOR operation is
 * to check whether the corresponding bits are same or not between two numbers. In other words, if
 * there is any one bit between the two numbers is set to ‘1’ (e.g., any one bit in x is '1’), the
 * two numbers (e.g., a and b) are impossible to be the same.
 *
 * <p>Let’s extend the above conclusion to this problem:
 *
 * <p>We have a serise of numbers. We have value x = a ^ b, but we don’t know which two numbers are
 * a and b. But we know, there must be at least one bit in a and b are NOT the same. (because x is
 * NOT 0, there must be at least one bit in x is 1) In other words, in a certain bit, say kth bit, a
 * must be 0 and b must be 1, or vice versa. Look at the array, every number in the array may be a,
 * or b. For every number in the array，look at the kth bit , it has only two possible values: 0 or
 * 1. So if we divide the number in the arrary simplely by the kth bit, the array is divided into
 * two part, and a and b must be in different part. For each part, except the number a (or b), all
 * the other numbers must appear twice in the same part. Now the problem becomes the simple version
 * of single number we have seen before. Done.
 *
 * <p>Therefore, we have successfully transit the problem into two sub problems, in which a simple
 * loop with XOR operations will work well to solve..
 */
public class LC260_SingleNumber3 {
  public int[] singleNumber(int[] nums) {
    int xor = 0;
    for (int v : nums) {
      xor ^= v;
    }
    int mask = xor ^ (xor & (xor - 1));
    int[] res = new int[2];

    for (int v : nums) {
      if ((v & mask) != 0) res[0] ^= v;
      else res[1] ^= v;
    }

    return res;
  }
}
