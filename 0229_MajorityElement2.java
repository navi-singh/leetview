import java.util.ArrayList;
import java.util.List;

public class LC229_MajorityElement2 {
  public List<Integer> majorityElement(int[] nums) {
    List<Integer> res = new ArrayList<Integer>();

    if (nums == null || nums.length < 1) {
      return res;
    }
    Integer num1 = null, num2 = null;
    int count1 = 0, count2 = 0;
    for (int cur : nums) {
      if (num1 != null && num1.intValue() == cur) {
        count1++;
      } else if (num2 != null && num2.intValue() == cur) {
        count2++;
      } else if (count1 == 0) {
        num1 = cur;
        count1 = 1;
      } else if (count2 == 0) {
        num2 = cur;
        count2 = 1;
      } else {
        count1--;
        count2--;
      }
    }
    count1 = count2 = 0;
    for (int cur : nums) {
      if (cur == num1) {
        count1++;
      } else if (cur == num2) {
        count2++;
      }
    }
    if (count1 > nums.length / 3) {
      res.add(num1);
    }
    if (count2 > nums.length / 3) {
      res.add(num2);
    }
    return res;
  }
}
