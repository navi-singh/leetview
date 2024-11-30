import java.util.HashSet;
import java.util.Set;

public class LC_0128_LongestConsecutiveSequence {
  public int longestConsecutive(int[] nums) {
    Set<Integer> numSet = new HashSet<Integer>();
    int max = 0;
    for (int num : nums) {
      numSet.add(num);
    }
    for (int num : nums) {
      int count = 1;
      int down = num - 1;
      while (numSet.contains(down)) {
        numSet.remove(down);
        down--;
        count++;
      }
      int up = num + 1;
      while (numSet.contains(up)) {
        numSet.remove(up);
        up++;
        count++;
      }
      max = Math.max(max, count);
    }
    return max;
  }
}
