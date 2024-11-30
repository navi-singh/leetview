package com.lc;

public class LC_0004_MedianOfSortedArray {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int firstLen = nums1.length;
    int secondLen = nums2.length;
    if (firstLen > secondLen) {
      return findMedianSortedArrays(nums2, nums1);
    }
    int low = 0;
    int high = firstLen;
    while (low <= high) {
      int partitionFirst = (low + high) / 2;
      int partitionSec = (firstLen + secondLen + 1) / 2 - partitionFirst;

      int maxFirstLeft = (partitionFirst == 0) ? Integer.MIN_VALUE : nums1[partitionFirst - 1];
      int minFirstRight = (partitionFirst == firstLen) ? Integer.MAX_VALUE : nums1[partitionFirst];

      int maxSecondLeft = (partitionSec == 0) ? Integer.MIN_VALUE : nums2[partitionSec - 1];
      int minSecondRight = (partitionSec == secondLen) ? Integer.MAX_VALUE : nums2[partitionSec];
      if (maxFirstLeft <= minSecondRight && maxSecondLeft <= minFirstRight) {
        if ((firstLen + secondLen) % 2 == 0) {
          return ((double) Math.max(maxFirstLeft, maxSecondLeft)
                  + Math.min(minFirstRight, minSecondRight))
              / 2;
        } else {
          return Math.max(maxFirstLeft, maxSecondLeft);
        }
      } else if (maxFirstLeft > minSecondRight) {
        high = partitionFirst - 1;
      } else {
        low = partitionFirst + 1;
      }
    }
    return 0;
  }
}
