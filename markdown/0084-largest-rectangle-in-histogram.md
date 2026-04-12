---
description: HARD
---

# 84. Largest Rectangle in Histogram

Given an array of integers `heights` representing the histogram's bar height where the width of each bar is `1`, return the area of the largest rectangle in the histogram.

**Example 1:**

```text
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The largest rectangle has area 10 (bars at indices 2 and 3, height 5, width 2).
```

**Example 2:**

```text
Input: heights = [2,4]
Output: 4
```

**Constraints:**

- `1 <= heights.length <= 10^5`
- `0 <= heights[i] <= 10^4`

---

## Approach 1: Monotonic Stack

A monotonic increasing stack of indices is maintained. We push an index whenever the bar at `left` is taller than (or equal to) the bar at the stack's top, which means the current bar could extend the rectangle of the top bar to the right. When we encounter a shorter bar, the bar at the stack top can no longer extend further right, so we pop it and calculate its maximum rectangle: the height is `heights[popped]` and the width extends from the current `left` position back to the new stack top (or to the beginning of the array if the stack is empty). After processing all bars, remaining stack entries are flushed with `left` as the right boundary.

#### Complexity Analysis

- **Time complexity: O(n).** Each index is pushed and popped at most once.
- **Space complexity: O(n).** The stack can hold at most `n` indices.

```java
public int largestRectangleArea(int[] heights) {
    Stack<Integer> st = new Stack<Integer>();
    int left = 0, right = heights.length;
    int height = 0, width = 0, maxArea = 0;
    while (left < right) {
        if (st.empty() || heights[st.peek()] < heights[left]) {
            st.push(left++);
        } else {
            height = heights[st.pop()];
            width = st.empty() ? left : (left - st.peek() - 1);
            maxArea = Integer.max(maxArea, height * width);
        }
    }
    while (!st.empty()) {
        height = heights[st.pop()];
        width = st.empty() ? left : (left - st.peek() - 1);
        maxArea = Integer.max(maxArea, height * width);
    }
    return maxArea;
}
```

**Key insight:** Storing indices (rather than heights) in the stack lets us compute the exact width of each candidate rectangle by subtracting the new stack-top index from the current position.
