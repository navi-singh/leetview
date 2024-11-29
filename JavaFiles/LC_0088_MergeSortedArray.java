
public class LC88_MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int first = m - 1, second = n - 1, i = m + n - 1;
        for (; i >= 0; i--) {
            if (second < 0 || (first >= 0 && nums1[first] > nums2[second])) {
                nums1[i] = nums1[first--];
            } else {
                nums1[i] = nums2[second--];
            }
        }
    }
}