# [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

Given an array of integers `nums` sorted in non-decreasing order, find the starting and ending position of a given `target` value.

If `target` is not found in the array, return `[-1, -1]`.

You mustwrite an algorithm with`O(log n)` runtime complexity.

**Example 1:** 

```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
```

**Example 2:** 

```
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
```

**Example 3:** 

```
Input: nums = [], target = 0
Output: [-1,-1]
```

**Constraints:** 

- `0 <= nums.length <= 10^5`
- `-10^9<= nums[i]<= 10^9`
- `nums` is a non-decreasing array.
- `-10^9<= target<= 10^9`

### Complexity Analysis

Time Complexity: O(logN) considering there are N elements in the array. This is because binary search takes logarithmic time to scan an array of N elements. Why? Because at each step we discard half of the array we are scanning and hence, we're done after a logarithmic number of steps. We simply perform binary search twice in this case.

Space Complexity: O(1) since we only use space for a few variables and our result array, all of which require constant space.
```java
public class LC_0034_FindFirstLastInSortedArray {
  public int[] searchRange(int[] nums, int target) {
    int[] res = {-1, -1};
    int left = binarySearch(nums, target, true);
    if (left == nums.length || nums[left] != target) {
      return res;
    }
    res[0] = left;
    res[1] = binarySearch(nums, target, false) - 1;
    return res;
  }

  private int binarySearch(int[] nums, int target, boolean isLeft) {
    int left = 0, right = nums.length;

    while (left < right) {
      int mid = left + (right - left) / 2;
      if ((nums[mid] > target) || (isLeft && nums[mid] == target)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }
}
```

```python
class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:

        lower_bound = self.findBound(nums, target, True)
        if lower_bound == -1:
            return [-1, -1]

        upper_bound = self.findBound(nums, target, False)

        return [lower_bound, upper_bound]

    def findBound(self, nums: List[int], target: int, isFirst: bool) -> int:

        N = len(nums)
        begin, end = 0, N - 1
        while begin <= end:
            mid = int((begin + end) / 2)

            if nums[mid] == target:

                if isFirst:
                    # This means we found our lower bound.
                    if mid == begin or nums[mid - 1] < target:
                        return mid

                    # Search on the left side for the bound.
                    end = mid - 1
                else:

                    # This means we found our upper bound.
                    if mid == end or nums[mid + 1] > target:
                        return mid

                    # Search on the right side for the bound.
                    begin = mid + 1

            elif nums[mid] > target:
                end = mid - 1
            else:
                begin = mid + 1

        return -1
```
