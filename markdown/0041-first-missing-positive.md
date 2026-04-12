---
description: HARD
---

# 41. First Missing Positive

Given an unsorted integer array `nums`, return the smallest missing positive integer.

You must implement an algorithm that runs in `O(n)` time and uses `O(1)` auxiliary space.

**Example 1:**

```text
Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array. The smallest missing positive is 3.
```

**Example 2:**

```text
Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.
```

**Example 3:**

```text
Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.
```

**Constraints:**

- `1 <= nums.length <= 10^5`
- `-2^31 <= nums[i] <= 2^31 - 1`

---

## Approach 1: Index as Hash Map (Cyclic Sort)

The key observation is that the answer must lie in the range `[1, n+1]` where `n` is the length of the array. We use the array itself as a hash map by placing each number `x` (where `1 <= x <= n`) at index `x - 1`. After rearranging, we scan for the first index where `nums[i] != i + 1` — that index plus one is the answer.

The while loop performs swaps to put each number in its correct bucket. The condition `nums[i] != nums[nums[i] - 1]` prevents infinite loops when the target bucket already holds the right value.

#### Complexity Analysis

- **Time complexity: O(n).** Each element is moved at most once to its correct position, so the total number of swaps across all iterations is bounded by `n`.
- **Space complexity: O(1).** The rearrangement is done in-place with only a single temp variable.

```java
public class LC_0041_FirstMissingPositive {
  public int firstMissingPositive(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 1;
    }

    for (int i = 0; i < nums.length; i++) {
      while (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {

        int temp = nums[nums[i] - 1];
        nums[nums[i] - 1] = nums[i];
        nums[i] = temp;
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != i + 1) {
        return i + 1;
      }
    }
    return nums.length + 1;
  }
}
```

**Key insight:** By treating the array indices as buckets for the values `1..n`, we sidestep the need for extra space. Any value outside `[1, n]` is irrelevant to the answer and can be safely ignored during placement.
