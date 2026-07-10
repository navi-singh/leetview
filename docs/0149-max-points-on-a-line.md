---
description: HARD
---

# 149. Max Points on a Line

Given an array of `points` where `points[i] = [xi, yi]` represents a point on the **X-Y** plane, return the maximum number of points that lie on the same straight line.

**Example 1:**

```text
Input: points = [[1,1],[2,2],[3,3]]
Output: 3
```

**Example 2:**

```text
Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
```

**Constraints:**

- `1 <= points.length <= 300`
- `points[i].length == 2`
- `-10^4 <= xi, yi <= 10^4`
- All the `points` are **unique**.

---

## Approach: Slope-Based HashMap with Duplicate and Vertical Handling

For each anchor point `i`, compute the slope between `i` and every other point `j`. Use a HashMap keyed by slope to count how many points share the same slope with `i`. Handle vertical lines (same x-coordinate) and duplicate points separately. The maximum collinear points through anchor `i` is `max(slope count + duplicates, vertical count + duplicates)`.

#### Complexity Analysis

- **Time complexity: O(n^2).** For each of the n anchor points, we iterate over the remaining n-1 points.
- **Space complexity: O(n).** The slope HashMap is cleared after each anchor; at most n-1 entries at any time.

```java
package com.lc;

import java.util.HashMap;
import java.util.Map;

public class LC_0149_MaxPointsOnLine {
  public int maxPoints(int[][] points) {
    int max = 0;
    if (points == null || points.length == 0) return max;
    Map<Double, Integer> slopeCount = new HashMap<Double, Integer>();
    for (int i = 0; i < points.length; i++) {
      int vertical = 0;
      int duplicate = 1;
      for (int j = i + 1; j < points.length; j++) {
        if (points[j][0] == points[i][0]) {
          if (points[j][1] == points[i][1]) {
            duplicate++;
          } else {
            vertical++;
          }
        } else {
          double slope =
              points[j][1] == points[i][1]
                  ? 0.0
                  : (1 * (points[j][1] - points[i][1])) / (points[j][0] - points[i][0]);
          if (slopeCount.containsKey(slope)) {
            slopeCount.put(slope, slopeCount.get(slope) + 1);
          } else {
            slopeCount.put(slope, 1);
          }
        }
      }
      for (int counts : slopeCount.values()) {
        if (counts + duplicate > max) {
          max = counts + duplicate;
        }
      }
      max = Math.max(vertical + duplicate, max);
      slopeCount.clear();
    }
    return max;
  }
}
```

**Key insight:** Duplicates must be counted separately and added to every group, because a duplicate point lies on every line through the anchor; initializing `duplicate = 1` accounts for the anchor point itself in the final count.
