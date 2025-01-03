# [1539. Kth Missing Positive Number](https://leetcode.com/problems/kth-missing-positive-number/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

Given an array `arr` of positive integers sorted in a **strictly increasing order** , and an integer `k`.

Return the `k^th` **positive**  integer that is **missing**  from this array.

**Example 1:** 

```
Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5^thmissing positive integer is 9.
```

**Example 2:** 

```
Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2^nd missing positive integer is 6.
```

### Solution
#### Binary solution
**Complexity Analysis**
Time complexity: O(logN), where N is a number of elements in the input array.
Space complexity: O(1), we don't allocate any additional data structures.


```python
class Solution:
    def findKthPositive(self, arr: List[int], k: int) -> int:
        left, right = 0, len(arr) - 1
        while left <= right:
            pivot = (left + right) // 2
            # If number of positive integers
            # which are missing before arr[pivot]
            # is less than k -->
            # continue to search on the right.
            if arr[pivot] - pivot - 1 < k:
                left = pivot + 1
            # Otherwise, go left.
            else:
                right = pivot - 1

        # At the end of the loop, left = right + 1,
        # and the kth missing is in-between arr[right] and arr[left].
        # The number of integers missing before arr[right] is
        # arr[right] - right - 1 -->
        # the number to return is
        # arr[right] + k - (arr[right] - right - 1) = k + left
        return left + k
```

#### Linear solution
**Complexity Analysis**

Time complexity: O(N) because in the worst case, we have to parse all array elements.

Space complexity: O(1), we don't allocate any additional data structures.

```python
class Solution:
    def findKthPositive(self, arr: List[int], k: int) -> int:
        for num in arr:
            if num <= k:
                k += 1
            elif num > k:
                break
        return k
```

