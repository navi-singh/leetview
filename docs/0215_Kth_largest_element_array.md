
# [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

Given an integer array `nums` and an integer `k`, return the `k^th` largest element in the array.

Note that it is the `k^th` largest element in the sorted order, not the `k^th` distinct element.

Can you solve it without sorting?

**Example 1:** 

```
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
```

**Example 2:** 

```
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
```

**Constraints:** 

- `1 <= k <= nums.length <= 10^5`
- `-10^4 <= nums[i] <= 10^4`



```java
public class LC215_KthLargestElementInArray {
  public int findKthLargest(int[] nums, int k) {

    k = nums.length - k;
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      final int j = partition(nums, left, right);
      if (j < k) {
        left = j + 1;
      } else if (j > k) {
        right = j - 1;
      } else {
        break;
      }
    }
    return nums[k];
  }

  private int partition(int[] a, int left, int right) {

    int i = left + 1;
    int j = right;
    while (true) {
      while (i < right && a[i] < a[left]) {
        i++;
      }
      while (j > left && a[left] <= a[j]) {
        j--;
      }
      if (i >= j) {
        break;
      }
      swap(a, i, j);
    }
    swap(a, left, j);
    return j;
  }

  private void swap(int[] nums, int left, int right) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
  }
}

```






