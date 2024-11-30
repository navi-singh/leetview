import java.util.HashSet;
import java.util.Set;

public class LC_0219_ContainsDuplicate2 {
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    if (nums == null || nums.length < 1 || k == 0) {
      return false;
    }
    int i = 0;
    Set<Integer> dict = new HashSet<Integer>();
    for (int j = 0; j < nums.length; j++) {
      if (!dict.add(nums[j])) {
        return true;
      }
      if (dict.size() >= k + 1) {
        dict.remove(nums[i++]);
      }
    }
    return false;
  }
}
