public class LC167_TwoSum2 {
  public int[] twoSum(int[] numbers, int target) {
    if (numbers == null) {
      return null;
    }
    int start = 0, end = numbers.length - 1;
    while (start < end) {
      int temp = numbers[start] + numbers[end];
      System.out.println(numbers[start] + ":" + numbers[end]);
      if (temp == target) {
        return new int[] {start + 1, end + 1};
      } else if (temp < target) {
        start++;
      } else {
        end--;
      }
    }
    return null;
  }
}
