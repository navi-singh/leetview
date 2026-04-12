---
description: MEDIUM
---

# 223. Rectangle Area

Given the coordinates of two **axis-aligned** rectangles in a 2D plane, return the total area covered by the two rectangles.

The first rectangle is defined by its **bottom-left** corner `(ax1, ay1)` and its **top-right** corner `(ax2, ay2)`.

The second rectangle is defined by its **bottom-left** corner `(bx1, by1)` and its **top-right** corner `(bx2, by2)`.

**Example 1:**

```text
Input: ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
Output: 45
```

**Example 2:**

```text
Input: ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
Output: 16
```

**Constraints:**

- `-10^4 <= ax1 <= ax2 <= 10^4`
- `-10^4 <= ay1 <= ay2 <= 10^4`
- `-10^4 <= bx1 <= bx2 <= 10^4`
- `-10^4 <= by1 <= by2 <= 10^4`

---

## Approach: Area Subtraction

Compute the combined area of both rectangles and then subtract the overlap. The overlap rectangle's horizontal span is `[max(A, E), min(C, G)]` and vertical span is `[max(B, F), min(D, H)]`. If the rectangles do not overlap (one is entirely to the left, right, above, or below the other), return the sum of both areas directly.

#### Complexity Analysis

- **Time complexity: O(1).** All operations are constant-time arithmetic.
- **Space complexity: O(1).** No additional data structures are used.

```java
package com.lc;

public class LC_0223_RectangleArea {
  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    int totalArea = (C - A) * (D - B) + (G - E) * (H - F);
    if (C < E || G < A || D < F || H < B) {
      return totalArea;
    }
    int left = Math.max(A, E);
    int right = Math.min(C, G);
    int bottom = Math.max(B, F);
    int top = Math.min(D, H);
    return totalArea - (right - left) * (top - bottom);
  }
}
```

**Key insight:** Total covered area equals the sum of both rectangle areas minus their intersection area. Detecting non-overlap early avoids computing a negative or zero intersection.
