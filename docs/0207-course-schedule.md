---
description: MEDIUM
---

# 207. Course Schedule

There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you must take course `bi` first if you want to take course `ai`.

Return `true` if you can finish all courses. Otherwise, return `false`.

**Example 1:**

```text
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
```

**Example 2:**

```text
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
```

**Constraints:**

- `1 <= numCourses <= 2000`
- `0 <= prerequisites.length <= 5000`
- `prerequisites[i].length == 2`
- `0 <= ai, bi < numCourses`
- All the pairs `prerequisites[i]` are unique.

---

## Approach: DFS Cycle Detection with Three-State Coloring

Build an adjacency list from `prerequisites`. For each unvisited node, run a DFS using a `visit` array with three states: unvisited (`0`), in-progress (`-1`), and done (`1`). If DFS reaches a node marked in-progress, a cycle exists and the courses cannot all be finished.

#### Complexity Analysis

- **Time complexity: O(V + E).** Each vertex and edge is visited once.
- **Space complexity: O(V + E).** The adjacency map and visit array together use linear space.

```java
public class LC_0207_CourseSchedule {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if (prerequisites == null) {
      return false;
    }
    int len = prerequisites.length;
    int[] visit = new int[numCourses];
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int[] a : prerequisites) {
      List<Integer> lis = map.getOrDefault(a[1], new ArrayList<>());
      lis.add(a[0]);
      map.put(a[1], lis);
    }

    for (int i = 0; i < numCourses; i++) {
      if (!canFinishDFS(map, visit, i)) {
        return false;
      }
    }
    return true;
  }

  private boolean canFinishDFS(Map<Integer, List<Integer>> map, int[] visit, int i) {
    if (visit[i] == -1) {
      return false;
    }
    if (visit[i] == 1) {
      return true;
    }
    visit[i] = -1;
    if (map.containsKey(i)) {
      for (int j : map.get(i)) {
        if (!canFinishDFS(map, visit, j)) {
          return false;
        }
      }
    }
    visit[i] = 1;
    return true;
  }
}
```

**Key insight:** Marking a node `-1` (in-progress) before exploring its neighbors allows the DFS to detect back edges—a back edge to an in-progress node proves the graph has a cycle.
