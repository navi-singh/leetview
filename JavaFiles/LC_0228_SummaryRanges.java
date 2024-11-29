import java.util.ArrayList;
import java.util.List;

public class LC228_SummaryRanges {
  public List<String> summaryRanges(int[] nums) {
    List<String> res = new ArrayList<String>();
    if (nums == null || nums.length < 1) {
      return res;
    }
    int start = nums[0], end = nums[0];
    for (int i = 1; i <= nums.length; i++) {
      if (i < nums.length && nums[i] == end + 1) {
        end = nums[i];
      } else {
        if (start == end) {
          res.add(String.valueOf(start));
        } else {
          res.add(String.format("%d->%d", start, end));
        }
        if (i < nums.length) {
          start = end = nums[i];
        }
      }
    }
    return res;
  }
}
