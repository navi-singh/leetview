import java.util.HashSet;
import java.util.Set;

public class LC_0217_ContainsDuplicate {
  public boolean containsDuplicate(int[] nums) {
    if (nums == null || nums.length < 1) {
      return false;
    }
    Set<Integer> dict = new HashSet<>();
    for (int num : nums) {
      if (dict.contains(num)) {
        return true;
      }
      dict.add(num);
    }
    return false;
  }
}
