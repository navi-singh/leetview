---
description: MEDIUM
---

# 167. Two Sum II - Input Array Is Sorted

Given a **1-indexed** array of integers `numbers` that is already **sorted in non-decreasing order**, find two numbers such that they add up to a specific `target` number. Let these two numbers be `numbers[index1]` and `numbers[index2]` where `1 <= index1 < index2 <= numbers.length`.

Return the indices of the two numbers, `index1` and `index2`, **added by one** as an integer array `[index1, index2]` of length 2.

The tests are generated such that there is **exactly one solution**. You **may not** use the same element twice.

Your solution must use only constant extra space.

**Example 1:**

```text
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
```

**Example 2:**

```text
Input: numbers = [2,3,4], target = 6
Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
```

**Example 3:**

```text
Input: numbers = [-1,0], target = -1
Output: [1,2]
Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
```

**Constraints:**

- `2 <= numbers.length <= 3 * 10^4`
- `-1000 <= numbers[i] <= 1000`
- `numbers` is sorted in **non-decreasing** order.
- `-1000 <= target <= 1000`
- The tests are generated such that there is **exactly one solution**.

---

## Approach: Two Pointers

Place one pointer at the start and one at the end of the array. If their sum equals the target, return their 1-indexed positions. If the sum is too small, advance the left pointer to increase the sum. If the sum is too large, retreat the right pointer to decrease it.

#### Complexity Analysis

- **Time complexity: O(n).** Each pointer moves at most `n` steps before the two meet.
- **Space complexity: O(1).** Only two index variables are used.

```java
package com.lc;

public class LC_0167_TwoSum2 {
  public int[] twoSum(int[] numbers, int target) {
    if (numbers == null) {
      return null;
    }
    int start = 0, end = numbers.length - 1;
    while (start < end) {
      int temp = numbers[start] + numbers[end];
      System.out.println(numbers[start] + ":" + numbers[end]);
      if (temp == target) {
        return new int[] {start + 1, end + 1};
      } else if (temp < target) {
        start++;
      } else {
        end--;
      }
    }
    return null;
  }
}
```

**Key insight:** The sorted order lets us make a binary decision at each step — moving the left pointer up increases the sum, moving the right pointer down decreases it — so we converge to the answer without a hash map.
