---
description: HARD
---

# 218. The Skyline Problem

A city's skyline is the outer contour of the silhouette formed by all the buildings collectively when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.

The geometric information of each building is given in the array `buildings` where `buildings[i] = [lefti, righti, heighti]`:

- `lefti` is the x coordinate of the left edge of the `i`th building.
- `righti` is the x coordinate of the right edge of the `i`th building.
- `heighti` is the height of the `i`th building.

You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height `0`.

The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form `[[x1,y1],[x2,y2],...]`. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate `0` and is used to mark the skyline's termination where the rightmost building ends.

**Example 1:**

```text
Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
```

**Example 2:**

```text
Input: buildings = [[0,2,3],[2,5,3]]
Output: [[0,3],[5,0]]
```

**Constraints:**

- `1 <= buildings.length <= 10^4`
- `0 <= lefti < righti <= 2^31 - 1`
- `1 <= heighti <= 2^31 - 1`
- `buildings` is sorted by `lefti` in non-decreasing order.

---

## Approach: Event-Based Sweep with Max-Heap

Convert each building into two edge events: a start edge and an end edge. Sort all edges by x-coordinate (breaking ties by: starts before ends at the same x, tallest start first, shortest end first). Sweep through the sorted edges using a max-heap of active heights. At each start edge, if the new height exceeds the current maximum, record a key point. At each end edge, remove that height from the heap; if the max changes, record a key point.

#### Complexity Analysis

- **Time complexity: O(n^2).** Sorting edges is O(n log n), but `PriorityQueue.remove(height)` is O(n), making each end-edge processing O(n) in the worst case.
- **Space complexity: O(n).** The edges list and height heap each hold O(n) entries.

```java
public class LC_0218_SkylineProject {
  class Edge {
    int x;
    int height;
    boolean isStart;

    public Edge(int x, int height, boolean isStart) {
      this.x = x;
      this.height = height;
      this.isStart = isStart;
    }
  }

  public List<List<Integer>> getSkyline(int[][] buildings) {
    List<List<Integer>> result = new ArrayList<>();
    if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
      return result;
    }
    List<Edge> edges = new ArrayList<>();
    for (int[] building : buildings) {
      edges.add(new Edge(building[0], building[2], true));
      edges.add(new Edge(building[1], building[2], false));
    }
    Collections.sort(
        edges,
        new Comparator<Edge>() {
          public int compare(Edge a, Edge b) {
            if (a.x != b.x) {
              return Integer.compare(a.x, b.x);
            }
            if (a.isStart && b.isStart) {
              return Integer.compare(b.height, a.height);
            }
            if (!a.isStart && !b.isStart) {
              return Integer.compare(a.height, b.height);
            }
            return a.isStart ? -1 : 1;
          }
        });
    PriorityQueue<Integer> heightHeap = new PriorityQueue<>(10, Collections.reverseOrder());
    for (Edge edge : edges) {
      if (edge.isStart) {
        if (heightHeap.isEmpty() || edge.height > heightHeap.peek()) {
          result.add(new ArrayList<>(Arrays.asList(edge.x, edge.height)));
        }
        heightHeap.add(edge.height);
      } else {
        heightHeap.remove(edge.height);
        if (heightHeap.isEmpty()) {
          result.add(new ArrayList<>(Arrays.asList(edge.x, 0)));
        } else if (edge.height > heightHeap.peek()) {
          result.add(new ArrayList<>(Arrays.asList(edge.x, heightHeap.peek())));
        }
      }
    }

    return result;
  }
}
```

**Key insight:** The careful tie-breaking in the sort order (starts before ends, tallest start first at the same x) ensures that when two buildings share an edge, their transitions are merged correctly and no spurious key points are emitted.
