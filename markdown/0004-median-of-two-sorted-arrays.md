---
description: HARD
---

# 4. Median of Two Sorted Arrays

Given two sorted arrays `nums1` and `nums2` of size `m` and `n` respectively, return the median of the two sorted arrays.

The overall run time complexity should be `O(log(m + n))`.

**Example 1:**

```text
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
```

**Example 2:**

```text
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
```

**Example 3:**

```text
Input: nums1 = [0,0], nums2 = [0,0]
Output: 0.00000
```

**Example 4:**

```text
Input: nums1 = [], nums2 = [1]
Output: 1.00000
```

**Constraints:**

- `nums1.length == m`
- `nums2.length == n`
- `0 <= m <= 1000`
- `0 <= n <= 1000`
- `1 <= m + n <= 2000`
- `-10^6 <= nums1[i], nums2[i] <= 10^6`

---

## Approach 1: Binary Search on Partition

Binary search on the smaller array to find the correct partition point that splits the combined array into two equal halves. For a valid partition, the maximum of the left halves must be less than or equal to the minimum of the right halves. Sentinel values (`Integer.MIN_VALUE` / `Integer.MAX_VALUE`) handle edge cases where a partition is at the boundary of an array.

#### Complexity Analysis

- **Time complexity: O(log(min(m, n))).** Binary search runs on the smaller of the two arrays.
- **Space complexity: O(1).** Only a constant number of variables are used.

```java
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
```

**Key insight:** By always binary-searching on the smaller array and computing the second partition index as `(m + n + 1) / 2 - partitionFirst`, we guarantee both halves are equally sized and need only adjust one partition to find the global median.
