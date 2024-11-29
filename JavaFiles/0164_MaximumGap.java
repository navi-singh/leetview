public class LC164_MaximumGap {
  class Bucket {
    int min;
    int max;

    public Bucket() {
      min = Integer.MAX_VALUE;
      max = Integer.MIN_VALUE;
    }
  }

  public int maximumGap(int[] nums) {
    if (nums == null || nums.length < 2) {
      return 0;
    }
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      min = Math.min(min, nums[i]);
      max = Math.max(max, nums[i]);
    }
    Bucket buckets[] = new Bucket[nums.length + 1];
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new Bucket();
    }
    double interval = (double) nums.length / (max - min);
    for (int i = 0; i < nums.length; i++) {
      int index = (int) ((nums[i] - min) * interval);
      if (buckets[index].min == Integer.MAX_VALUE) {
        buckets[index].min = nums[i];
        buckets[index].max = nums[i];
      } else {
        buckets[index].min = Math.min(nums[i], buckets[index].min);
        buckets[index].max = Math.max(nums[i], buckets[index].max);
      }
    }
    int res = 0;
    int prev = buckets[0].max;
    for (int i = 1; i < buckets.length; i++) {
      if (buckets[i].min != Integer.MAX_VALUE) {
        res = Math.max(res, buckets[i].min - prev);
        prev = buckets[i].max;
      }
    }
    return res;
  }
}
