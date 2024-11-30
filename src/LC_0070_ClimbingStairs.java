public class LC_0070_ClimbingStairs{
  /**
   * Time complexity : O(n) Single loop upto nnn. Space complexity : O(n) steps array of size nnn is
   * used.
   */
  public int climbStairsUsingDP(int n) {
    if (n <= 2) {
      return n;
    }
    int[] steps = new int[n + 1];
    steps[0] = steps[1] = 1;
    for (int i = 2; i <= n; i++) {
      steps[i] = steps[i - 1] + steps[i - 2];
    }
    return steps[n];
  }

  /**
   * Time complexity : O(n) Single loop upto nnn. Space complexity : O(1) steps array of size nnn is
   * used.
   */
  public int climbStairs(int n) {
    if (n <= 2) {
      return n;
    }

    int first = 1, second = 2;
    int temp = 0;
    for (int i = 3; i <= n; i++) {
      temp = first + second;
      first = second;
      second = temp;
    }
    return second;
  }
}
