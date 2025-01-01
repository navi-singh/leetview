# [31. Next Permutation](https://leetcode.com/problems/next-permutation/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

A **permutation**  of an array of integers is an arrangement of its members into a sequence or linear order.

- For example, for `arr = [1,2,3]`, the following are all the permutations of `arr`: `[1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1]`.

The **next permutation**  of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the **next permutation**  of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

- For example, the next permutation of `arr = [1,2,3]` is `[1,3,2]`.
- Similarly, the next permutation of `arr = [2,3,1]` is `[3,1,2]`.
- While the next permutation of `arr = [3,2,1]` is `[1,2,3]` because `[3,2,1]` does not have a lexicographical larger rearrangement.

Given an array of integers `nums`, find the next permutation of `nums`.

The replacement must be **<a href="http://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in place</a>**  and use only constant extra memory.

**Example 1:** 

```
Input: nums = [1,2,3]
Output: [1,3,2]
```

**Example 2:** 

```
Input: nums = [3,2,1]
Output: [1,2,3]
```

**Example 3:** 

```
Input: nums = [1,1,5]
Output: [1,5,1]
```
```
  Complete printout 1,2,3,4 -> 1,2,4,3,-> 1,3,2,4,-> 1,3,4,2,-> 1,4,2,3,-> 1,4,3,2,-> 2,1,3,4,->
  2,1,4,3,-> 2,3,1,4,-> 2,3,4,1,-> 2,4,1,3,-> 2,4,3,1,-> 3,1,2,4,-> 3,1,4,2,-> 3,2,1,4,->
  3,2,4,1,-> 3,4,1,2,-> 3,4,2,1,-> 4,1,2,3,-> 4,1,3,2,-> 4,2,1,3,-> 4,2,3,1,-> 4,3,1,2,->
  4,3,2,1,->
 
```

## Solution
### Complexity Analysis

Let n be the size of the nums array.

**Time complexity: O(n)**

The first while loop runs at most n iterations, decrementing the variable i as it searches for the first decreasing element from the right. In the worst case, it checks all elements, so it takes O(n) time.

The second while loop also runs at most n iterations, decrementing the variable j as it searches for the smallest element larger than nums[i]. Similarly, it can take O(n) time.

The reverse function is called on a portion of the array, from index i + 1 to the end. In the worst case, this can cover the entire array, leading to a time complexity of O(n).

The swap function runs in constant time, O(1), since it only exchanges two elements.

Therefore, the overall time complexity is O(n).

**Space complexity: O(1)**

The function operates in-place on the nums array, meaning no extra space is used for storing additional data.

Only a few constant space variables (i, j, and temp) are used.

The built-in swap and reverse functions do not require additional space beyond what is already present in the input array.

Hence, the space complexity is O(1).

```python
class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        swap_index = len(nums) - 2
        while swap_index >= 0 and nums[swap_index] >= nums[swap_index + 1]:
            swap_index -= 1
        if swap_index >= 0:
            i = len(nums) - 1
            while i >= 0 and nums[swap_index] >= nums[i]:
                i -= 1;
            self.swap(nums, swap_index, i)
        self.reverse(nums, swap_index + 1, len(nums)-1)
    
    def swap(self, nums, i, j):
        temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp

    def reverse(self, nums, i, j):
        while i < j:
            self.swap(nums, i, j)
            i += 1
            j -= 1
```

<details>
  <summary>Java solution</summary>
  
```java
public class LC_0031_NextPermutation {
  public void nextPermutation(int[] nums) {
    if (nums.length <= 1) return;
    int total = nums.length;
    int swapIndex = total - 2;
    while (swapIndex >= 0 && nums[swapIndex] >= nums[swapIndex + 1]) swapIndex--;
    if (swapIndex >= 0) {
      int index = total - 1;
      while (index >= 0 && nums[swapIndex] >= nums[index]) index--;
      swap(nums, swapIndex, index);
    }
    reverse(nums, swapIndex + 1, total - 1);
  }

  private void reverse(int[] nums, int first, int second) {
    while (first < second) swap(nums, first++, second--);
  }

  private void swap(int[] nums, int first, int second) {
    int temp = nums[first];
    nums[first] = nums[second];
    nums[second] = temp;
  }
}
```
  
</details>
