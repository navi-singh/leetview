import java.util.TreeSet;

public class LC220_ContainsDuplicate3 {
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if (nums == null || nums.length < 1 || k < 0 || t < 0) {
      return false;
    }
    TreeSet<Long> set = new TreeSet<Long>();
    for (int i = 0; i < nums.length; i++) {
      long cur = (long) nums[i];
      if ((set.floor(cur) != null && cur <= set.floor(cur) + t)
          || (set.ceiling(cur) != null && cur >= set.ceiling(cur) - t)) {
        return true;
      }
      set.add(cur);
      if (i >= k) {
        set.remove((long) nums[i - k]);
      }
    }
    return false;
  }
}
