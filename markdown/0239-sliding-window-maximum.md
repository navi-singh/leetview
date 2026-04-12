---
description: HARD
---

# 239. Sliding Window Maximum

You are given an array of integers `nums`, there is a sliding window of size `k` which is moving from the very left of the array to the very right. You can only see the `k` numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

**Example 1:**

```text
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

**Example 2:**

```text
Input: nums = [1], k = 1
Output: [1]
```

**Constraints:**

- `1 <= nums.length <= 10^5`
- `-10^4 <= nums[i] <= 10^4`
- `1 <= k <= nums.length`

---

## Approach: Monotonic Deque

Maintain a deque of indices in decreasing order of their corresponding values. For each new index `i`:
1. Remove the front of the deque if it is out of the current window (`i - k`).
2. Remove from the back all indices whose values are less than `nums[i]` (they can never be the window maximum).
3. Add `i` to the back.
4. Once the first full window is formed (`i + 1 >= k`), record `nums[deque.front]` as the maximum.

#### Complexity Analysis

- **Time complexity: O(n).** Each index is added to and removed from the deque at most once.
- **Space complexity: O(k).** The deque holds at most `k` indices at any time.

```java
package com.lc;

import java.util.LinkedList;

public class LC_0239_SlidingWindowMaximum {
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length < 1 || k < 1) {
      return null;
    }

    // 1 2 3 4 5 k =3
    int[] res = new int[nums.length - k + 1];
    LinkedList<Integer> deque = new LinkedList<Integer>();
    for (int i = 0; i < nums.length; i++) {
      if (!deque.isEmpty() && deque.peekFirst() == i - k) {
        deque.poll();
      }
      while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
        deque.removeLast();
      }
      deque.offer(i);
      if (i + 1 >= k) {
        res[i - k + 1] = nums[deque.peek()];
      }
    }
    return res;
  }
}
```

**Key insight:** The deque stores indices rather than values so that expiry (sliding the window past an old index) can be detected in O(1); the invariant that the deque is always decreasing ensures the front is always the window maximum.
