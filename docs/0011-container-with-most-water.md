---
description: MEDIUM
---

# 11. Container With Most Water

You are given an integer array `height` of length `n`. There are `n` vertical lines drawn such that the two endpoints of the `i`th line are `(i, 0)` and `(i, height[i])`.

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

**Notice** that you may not slant the container.

## In Plain Terms

Each number is the height of a vertical wall standing at that index. Pick **two walls** so that the water trapped between them is as large as possible. The water is a rectangle: its **width** is the distance between the two walls, and its **height** is the *shorter* of the two walls (water spills over the lower one). Maximize `width × min(height)`.

```text
height = [1,8,6,2,5,4,8,3,7]

8 |  █           █
7 |  █           █     █
6 |  █  █        █     █
5 |  █  █     █  █     █
4 |  █  █     █  █  █  █
3 |  █  █     █  █  █  █  █
2 |  █  █  █  █  █  █  █  █
1 |█ █  █  █  █  █  █  █  █
  +------------------------
   0 1  2  3  4  5  6  7  8
        ↑                 ↑
      left=1 (h=8)     right=8 (h=7)
   width = 8-1 = 7,  height = min(8,7) = 7  →  area = 49
```

**Example 1:**

```text
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The vertical lines are represented by [1,8,6,2,5,4,8,3,7]. The max area of water the container can contain is 49.
```

**Example 2:**

```text
Input: height = [1,1]
Output: 1
```

**Example 3:**

```text
Input: height = [4,3,2,1,4]
Output: 16
```

**Example 4:**

```text
Input: height = [1,2,1]
Output: 2
```

**Constraints:**

- `2 <= height.length <= 3 * 10^4`
- `0 <= height[i] <= 3 * 10^4`

---

## Approach 1: Two Pointers

Place one pointer at each end of the array. At each step, compute the area formed by the two pointers (`min(height[left], height[right]) * (right - left)`) and update the maximum. Always advance the pointer on the shorter side, because keeping the taller side gives the only chance of finding a larger container as the width shrinks.

#### Complexity Analysis

- **Time complexity: O(n).** The two pointers together traverse the array exactly once.
- **Space complexity: O(1).** Only the two pointers and the running maximum are stored.

```java
public int maxArea(int[] height) {
    int max = 0;
    int left = 0, right = height.length - 1;
    while (left < right) {
        max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
        if (height[left] < height[right]) left++;
        else right--;
    }
    return max;
}
```

**Key insight:** Moving the shorter-side pointer is provably safe — any container formed by keeping the shorter line while moving the taller pointer inward can only be smaller (shorter width, bounded by the same shorter height), so no valid larger area is ever skipped.
